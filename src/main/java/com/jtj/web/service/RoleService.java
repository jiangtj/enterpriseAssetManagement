package com.jtj.web.service;

import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.RoleDao;
import com.jtj.web.dto.RoleDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Role;
import com.jtj.web.service.base.CurdService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface RoleService extends CurdService<Role,RoleDto,RoleDao> {

    ResultDto<List<KeyValue>> getRoleMap();

    ResultDto<Object> getPermission(Long roleId);

    ResultDto<Object> updatePermission(Long roleId, Long[] permissionIds);
}
