package com.jtj.web.service.impl;

import com.jtj.web.common.AssetException;
import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.PermissionDao;
import com.jtj.web.dao.RoleDao;
import com.jtj.web.dto.PermissionDto;
import com.jtj.web.dto.RoleDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Permission;
import com.jtj.web.entity.Role;
import com.jtj.web.service.PermissionService;
import com.jtj.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Service
public class PermissionServiceImpl
        extends BaseServiceImpl<Permission,PermissionDto,PermissionDao>
        implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    /*@Override
    public ResultDto<Object> add(Permission permission) {
        ResultDto<Object> result = new ResultDto<>();
        result.setResultCode(permissionDao.add(permission) == 1?ResultCode.SUCCESS:ResultCode.OPERATE_FAIL);
        return result;
    }

    @Override
    public ResultDto<Object> delete(Long[] ids) throws AssetException {
        ResultDto<Object> result = new ResultDto<>();
        int count = permissionDao.delete(ids);
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
    public ResultDto<Object> update(Permission permission) {
        ResultDto<Object> result = new ResultDto<>();
        result.setResultCode(permissionDao.update(permission) == 1?ResultCode.SUCCESS:ResultCode.OPERATE_FAIL);
        return result;
    }

    @Override
    public ResultDto<PageDto<Permission>> getList(PermissionDto dto) {
        ResultDto<PageDto<Permission>> result = new ResultDto<>(ResultCode.SUCCESS);
        PageDto<Permission> page = new PageDto<>();
        page.setList(permissionDao.getList(dto));
        page.setCount(permissionDao.getNum(dto));
        result.setObject(page);
        return result;
    }*/

}
