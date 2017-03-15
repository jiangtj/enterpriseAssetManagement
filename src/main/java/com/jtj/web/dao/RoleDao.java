package com.jtj.web.dao;

import com.jtj.web.dto.UserDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Role;
import com.jtj.web.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Mapper
public interface RoleDao {

    List<KeyValue> getRoleMap();

}
