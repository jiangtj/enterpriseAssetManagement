package com.jtj.web.service.impl;

import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.exception.AssetException;
import com.jtj.web.dao.PermissionDao;
import com.jtj.web.dao.PointDao;
import com.jtj.web.dao.RoleDao;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Permission;
import com.jtj.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PointDao pointDao;
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public RoleDao getRepository() {
        return roleDao;
    }

    @Override
    public ResultDto<Object> delete(Long[] ids) throws AssetException {
        //todo 删除前修改用户为默认角色
        ResultDto<Object> result = new ResultDto<>();
        int count = roleDao.delete(ids);
        int all = ids.length;
        if (count == all){
            result.setResultCode(ResultCode.SUCCESS_DELETE);
            return result;
        }
        result.setResultCode(ResultCode.OPERATE_FAIL);
        result.setMessage("存在"+(all - count)+"/"+all+"数据有误！");
        throw new AssetException(result);
    }

    @Override
    public ResultDto<List<KeyValue>> getRoleMap() {
        ResultDto<List<KeyValue>> result = new ResultDto<>(ResultCode.SUCCESS);
        result.setObject(roleDao.getRoleMap());
        return result;
    }

    @Override
    public ResultDto<Object> getPermission(Long roleId) {
        ResultDto<Object> result = new ResultDto<>(ResultCode.SUCCESS);
        List<Permission> all = permissionDao.getAll();
        List<Permission> role = roleDao.getPermission(roleId);
        List<String> rolePermissions = role.stream().map(Permission::getCode).collect(Collectors.toList());
        Map<String,Object> map = new HashMap<>();
        map.put("all",all);
        map.put("role",rolePermissions);
        result.setObject(map);
        return result;
    }

    @Override
    public ResultDto<Object> updatePermission(Long roleId, Long[] permissionIds) {
        ResultDto<Object> result = new ResultDto<>(ResultCode.SUCCESS_PUT);
        //清除权限
        roleDao.clearPermission(roleId);
        //添加权限
        if (permissionIds.length == 0) return result;
        roleDao.addPermission(roleId,Arrays.asList(permissionIds));
        return result;
    }
}
