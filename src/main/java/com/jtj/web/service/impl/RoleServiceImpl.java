package com.jtj.web.service.impl;

import com.jtj.web.common.AssetException;
import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.RoleDao;
import com.jtj.web.dto.RoleDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Role;
import com.jtj.web.entity.User;
import com.jtj.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
