package com.jtj.web.dao;

import com.jtj.web.dto.UserDto;
import com.jtj.web.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Mapper
public interface UserDao {

    List<User> queryById(@Param("id") long id);

    User login(@Param("name") String name,@Param("password") String password);

    User getUserByName(@Param("name") String name);

    List<User> query(UserDto dto);

    int queryNum(UserDto dto);
}
