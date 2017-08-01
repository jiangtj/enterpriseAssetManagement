package com.jtj.web.service.impl;

import com.jtj.web.common.exception.AssetException;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.MenuDao;
import com.jtj.web.dao.PermissionDao;
import com.jtj.web.dao.PointDao;
import com.jtj.web.dao.RoleDao;
import com.jtj.web.dto.RoleDto;
import com.jtj.web.entity.*;
import com.jtj.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Service
public class RoleServiceImpl
        extends BaseServiceImpl<Role,RoleDto,RoleDao>
        implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private PointDao pointDao;
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public ResultDto<Object> delete(Long[] ids) throws AssetException {
        //todo 删除前修改用户为默认角色
        return super.delete(ids);
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
        ResultDto<Object> result = new ResultDto<>(ResultCode.SUCCESS);
        //清除权限
        roleDao.clearPermission(roleId);
        //添加权限
        if (permissionIds.length == 0) return result;
        roleDao.addPermission(roleId,Arrays.asList(permissionIds));
        return result;
    }

    @Override
    public ResultDto<Object> updatePoint(Long roleId, Long[] pointIds) {
        ResultDto<Object> result = new ResultDto<>(ResultCode.SUCCESS);
        roleDao.clearPoint(roleId);
        if (pointIds.length == 0) return result;
        List<Long> pointIdList= Arrays.asList(pointIds);
        List<Point> pointList = pointDao.getPointByIds(pointIdList);
        //获取向下菜单信息
        while (pointIdList.size() != 0){
            List<Point> tempList = pointDao.getPointByPids(pointIdList);
            pointList.addAll(tempList);
            pointIdList = tempList.stream().map(Point::getId).collect(Collectors.toList());
        }
        //获取权限
        Set<Long> pointSet = pointList.stream()
                .map(Point::getId)
                .collect(Collectors.toSet());
        roleDao.addPoint(roleId,pointSet);
        return result;
    }
}
