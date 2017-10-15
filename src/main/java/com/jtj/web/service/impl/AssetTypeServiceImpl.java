package com.jtj.web.service.impl;

import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.AssetTypeDao;
import com.jtj.web.dto.AssetTypeDto;
import com.jtj.web.entity.AssetType;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.service.AssetTypeService;
import com.jtj.web.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Service
public class AssetTypeServiceImpl
        extends BaseServiceImpl<AssetType,AssetTypeDto,AssetTypeDao>
        implements AssetTypeService {

    @Autowired
    private AssetTypeDao assetTypeDao;

    @Override
    public ResultDto<Object> add(AssetType t) {
        if (t.getPid() == null) {
            t.setPid(0L);
            t.setLevel(1);
        }else {
            AssetType type = assetTypeDao.getById(t.getPid());
            t.setLevel(type.getLevel() + 1);
        }
        return super.add(t);
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
        result.setObject( assetTypeDao.getMapByPid(pid));
        return result;
    }
}
