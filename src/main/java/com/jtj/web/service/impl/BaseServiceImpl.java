package com.jtj.web.service.impl;

import com.jtj.web.common.AssetException;
import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.BaseDao;
import com.jtj.web.dao.PermissionDao;
import com.jtj.web.dto.PermissionDto;
import com.jtj.web.entity.Permission;
import com.jtj.web.service.BaseService;
import com.jtj.web.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
public class BaseServiceImpl<Entity,Dto,Dao extends BaseDao<Entity,Dto>>
        implements BaseService<Entity,Dto> {

    @Autowired
    private Dao dao;


    @Override
    public ResultDto<Object> add(Entity t) {
        ResultDto<Object> result = new ResultDto<>();
        result.setResultCode(dao.add(t) == 1?ResultCode.SUCCESS:ResultCode.OPERATE_FAIL);
        return result;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ResultDto<Object> delete(Long[] ids) throws AssetException {
        ResultDto<Object> result = new ResultDto<>();
        int count = dao.delete(ids);
        int all = ids.length;
        if (count == all){
            result.setResultCode(ResultCode.SUCCESS);
            return result;
        }
        result.setResultCode(ResultCode.OPERATE_FAIL);
        result.setMessage("存在"+(all - count)+"/"+all+"数据有误！");
        throw new AssetException(result);
    }

    @Override
    public ResultDto<Object> update(Entity t) {
        ResultDto<Object> result = new ResultDto<>();
        result.setResultCode(dao.update(t) == 1?ResultCode.SUCCESS:ResultCode.OPERATE_FAIL);
        return result;
    }

    @Override
    public ResultDto<PageDto<Entity>> getList(Dto dto) {
        ResultDto<PageDto<Entity>> result = new ResultDto<>(ResultCode.SUCCESS);
        PageDto<Entity> page = new PageDto<>();
        page.setList(dao.getList(dto));
        page.setCount(dao.getNum(dto));
        result.setObject(page);
        return result;
    }
}
