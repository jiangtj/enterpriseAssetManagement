package com.jtj.web.service;

import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.UserDto;
import com.jtj.web.entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface UserService extends BaseService<User,UserDto> {

    ResultDto<Object> login(HttpServletRequest request, HttpServletResponse response,
                            String name, String password,Long time);

    ResultDto<Object> logout(HttpServletRequest request, HttpServletResponse response);

    ResultDto<User> getUserById(long id);

    ResultDto<Object> updatePoint(Long id,Long Long);
}
