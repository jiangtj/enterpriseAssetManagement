package com.jtj.web.dao;

import com.jtj.web.dto.PermissionDto;
import com.jtj.web.dto.RoleDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Permission;
import com.jtj.web.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Mapper
@Component
public interface PermissionDao extends BaseDao<Permission,PermissionDto>{

/*    int add(Permission permission);

    int delete(@Param("ids") Long[] ids);

    int update(Permission permission);

    List<Permission> getList(PermissionDto dto);

    int getNum(PermissionDto dto);*/

}
