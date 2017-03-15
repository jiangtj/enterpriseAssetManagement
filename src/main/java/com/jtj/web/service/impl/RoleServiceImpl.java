package com.jtj.web.service.impl;

import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.RoleDao;
import com.jtj.web.dto.RoleDto;
import com.jtj.web.entity.Role;
import com.jtj.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public ResultDto<Object> add(Role role) {
        return null;
    }

    @Override
    public ResultDto<Object> delete(long id) {
        return null;
    }

    @Override
    public ResultDto<Object> update(Role role) {
        return null;
    }

    @Override
    public ResultDto<Object> getList(RoleDto roleDto) {
        return null;
    }

    @Override
    public ResultDto<Object> getRoleById(long id) {
        return null;
    }

    @Override
    public ResultDto<Object> getRoleMap() {
        ResultDto<Object> result = new ResultDto<>(ResultCode.SUCCESS);
        result.setObject(roleDao.getRoleMap());
        return result;
    }
}
