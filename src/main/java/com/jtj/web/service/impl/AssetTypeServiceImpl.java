package com.jtj.web.service.impl;

import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.exception.AssetException;
import com.jtj.web.dao.AssetTypeDao;
import com.jtj.web.dto.AssetTypeDto;
import com.jtj.web.entity.AssetType;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.service.AssetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Service
public class AssetTypeServiceImpl implements AssetTypeService {

    @Autowired
    private AssetTypeDao assetTypeDao;

    //if you has redis, you can put it into redis
    private static Map<Long,AssetType> allAssetTypeMap = new HashMap<>();
    private static List<AssetType> allAssetTypeList = new ArrayList<>();

    @Override
    public AssetTypeDao getRepository() {
        return assetTypeDao;
    }

    //tree need
    @Override
    public AssetType getRootResource() {
        return new AssetType();
    }

    @Override
    public List<AssetType> getTreeResource() {
        return assetTypeDao.getAll();
    }

    @Override
    public List<AssetType> getTreeListResource() {
        return allAssetTypeList;
    }

    @Override
    public Map<Long, AssetType> getTreeMapResource() {
        return allAssetTypeMap;
    }

    @Override
    public ResultDto<Object> add(AssetType t) {
        ResultDto<Object> result = new ResultDto<>();
        if (t.getPid() == null) {
            t.setPid(0L);
        }
        assetTypeDao.add(t);
        refreshTreeData();
        result.setResultCode(ResultCode.SUCCESS_POST);
        result.setMessage("请刷新当前页面！");
        return result;
    }

    @Override
    public ResultDto<Object> update(AssetType t) {
        ResultDto<Object> result = new ResultDto<>();
        assetTypeDao.update(t);
        refreshTreeData();
        result.setResultCode(ResultCode.SUCCESS_PUT);
        result.setMessage("请刷新当前页面！");
        return result;
    }

    @Override
    public ResultDto<Object> delete(Long[] ids) throws AssetException {
        ResultDto<Object> result = new ResultDto<>();
        int count = assetTypeDao.delete(ids);
        int all = ids.length;
        if (count == all){
            refreshTreeData();
            result.setResultCode(ResultCode.SUCCESS_DELETE);
            result.setMessage("请刷新当前页面！");
            return result;
        }
        result.setResultCode(ResultCode.OPERATE_FAIL);
        result.setMessage("存在"+(all - count)+"/"+all+"数据有误！");
        throw new AssetException(result);
    }

    @Override
    public ResultDto<Object> deleteById(Long id) throws AssetException {
        ResultDto<Object> result = new ResultDto<>();
        AssetType assetType = getTreeMap().get(id);
        if (assetType.getNodes().size() != 0){
            throw new AssetException(new ResultDto<>(ResultCode.NOT_DELETE_USED));
        }
        assetTypeDao.delete(new Long[]{id});
        result.setResultCode(ResultCode.SUCCESS_DELETE);
        result.setMessage("请刷新当前页面！");
        return result;
    }

    @Override
    public ResultDto<List<AssetType>> getType(AssetTypeDto dto) {
        ResultDto<List<AssetType>> result = new ResultDto<>(ResultCode.SUCCESS);
        result.setObject( assetTypeDao.getType(dto));
        return result;
    }

    @Override
    public ResultDto<List<KeyValue>> getMapByPid(Long pid) {
        ResultDto<List<KeyValue>> result = new ResultDto<>(ResultCode.SUCCESS);
        result.setObject(getTreeMap().get(pid).getNodes().stream()
                .map(item-> new KeyValue(item.getId()+"",item.getName()))
                .collect(Collectors.toList()));
        return result;
    }
}
