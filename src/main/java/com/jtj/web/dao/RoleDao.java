package com.jtj.web.dao;

import com.jtj.web.dto.RoleDto;
import com.jtj.web.entity.KeyValue;
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
public interface RoleDao {

    int add(Role role);

    int delete(@Param("ids") Long[] ids);

    int update(Role role);

    List<Role> getList(RoleDto roleDto);

    int getNum(RoleDto roleDto);

    List<KeyValue> getRoleMap();

}
