package com.jtj.web.dao;

import com.jtj.web.dto.UserDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Mapper
@Component
public interface UserDao extends BaseDao<User,UserDto> {

    User getUserById(@Param("id") long id);

    User login(@Param("name") String name,@Param("password") String password);

    User getUserByName(@Param("name") String name);

    int updatePoint(@Param("id") Long id,@Param("pointId") Long pointId);

    int updateToNewPoint(@Param("from") Long from,@Param("to") Long to);

    List<KeyValue> getKeyValueByName(String name);
}
