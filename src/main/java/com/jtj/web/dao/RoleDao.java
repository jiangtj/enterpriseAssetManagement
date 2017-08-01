package com.jtj.web.dao;

import com.jtj.web.dto.RoleDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Permission;
import com.jtj.web.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Mapper
@Component
public interface RoleDao extends BaseDao<Role,RoleDto> {

    List<KeyValue> getRoleMap();

    int clearPermission(@Param("roleId") Long roleId);

    int addPermission(@Param("roleId") Long roleId,@Param("permissionIds") Collection<Long> permissionIds);

    int clearPoint(@Param("roleId") Long roleId);

    int addPoint(@Param("roleId") Long roleId,@Param("pointIds") Collection<Long> pointIds);

    List<Permission> getPermission(@Param("roleId") Long roleId);
}
