package com.jtj.web.service.impl;

import com.jtj.web.common.AssetException;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.MenuDao;
import com.jtj.web.dao.PointDao;
import com.jtj.web.dao.RoleDao;
import com.jtj.web.dto.RoleDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Menu;
import com.jtj.web.entity.Point;
import com.jtj.web.entity.Role;
import com.jtj.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
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
    public ResultDto<Object> updatePermission(Long roleId, Long[] menuIds) {
        ResultDto<Object> result = new ResultDto<>(ResultCode.SUCCESS);
        roleDao.clearPermission(roleId);
        if (menuIds.length == 0) return result;
        List<Long> menuIdList = Arrays.asList(menuIds);
        List<Menu> menuList = menuDao.getMenuByIds(menuIdList);
        Set<Long> needList = menuList.stream().map(Menu::getPid).collect(Collectors.toSet());
        //获取向上菜单信息
        while (needList.size() != 0){
            List<Menu> tempList = menuDao.getMenuByIds(needList);
            menuList.addAll(tempList);
            needList = tempList.stream().map(Menu::getPid).collect(Collectors.toSet());
        }
        //获取向下菜单信息
        while (menuIdList.size() != 0){
            List<Menu> tempList = menuDao.getMenuByPids(menuIdList);
            menuList.addAll(tempList);
            menuIdList = tempList.stream().map(Menu::getId).collect(Collectors.toList());
        }
        //获取权限
        Set<Long> permissionSet = menuList.stream()
                .filter(item -> item.getPermissionId() != null)
                .map(Menu::getPermissionId)
                .collect(Collectors.toSet());
        //添加权限
        roleDao.addPermission(roleId,permissionSet);
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
