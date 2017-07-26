package com.jtj.web.service.impl;

import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.PermissionDao;
import com.jtj.web.dto.PermissionDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Permission;
import com.jtj.web.service.PermissionService;
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

    @Override
    public ResultDto<List<KeyValue>> getMap() {
        ResultDto<List<KeyValue>> result = new ResultDto<>(ResultCode.SUCCESS);
        result.setObject(permissionDao.getMap());
        return result;
    }

    @Override
    public ResultDto<Object> addQuick(Permission permission) {
        String[] arr = permission.getUrl().split("/");
        StringBuilder builder = new StringBuilder();
        for (String temp : arr) builder.append(":").append(temp);
        permission.setName(builder.substring(2));
        return super.add(permission);
    }
}
