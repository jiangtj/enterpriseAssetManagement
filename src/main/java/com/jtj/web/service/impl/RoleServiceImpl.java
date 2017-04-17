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
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public ResultDto<Object> add(Role role) {
        ResultDto<Object> result = new ResultDto<>();
        result.setResultCode(roleDao.add(role) == 1?ResultCode.SUCCESS:ResultCode.OPERATE_FAIL);
        return result;
    }

    @Override
    public ResultDto<Object> delete(Long[] ids) throws AssetException {
        ResultDto<Object> result = new ResultDto<>();
        //todo 删除前修改用户为默认角色
        int count = roleDao.delete(ids);
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
    public ResultDto<Object> update(Role role) {
        ResultDto<Object> result = new ResultDto<>();
        result.setResultCode(roleDao.update(role) == 1?ResultCode.SUCCESS:ResultCode.OPERATE_FAIL);
        return result;
    }

    @Override
    public ResultDto<PageDto<Role>> getList(RoleDto dto) {
        ResultDto<PageDto<Role>> result = new ResultDto<>(ResultCode.SUCCESS);
        PageDto<Role> page = new PageDto<>();
        page.setList(roleDao.getList(dto));
        page.setCount(roleDao.getNum(dto));
        result.setObject(page);
        return result;
    }

    @Override
    public ResultDto<List<KeyValue>> getRoleMap() {
        ResultDto<List<KeyValue>> result = new ResultDto<>(ResultCode.SUCCESS);
        result.setObject(roleDao.getRoleMap());
        return result;
    }
}
