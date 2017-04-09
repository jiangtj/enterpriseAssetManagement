package com.jtj.web.service;

import com.jtj.web.common.AssetException;
import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.UserDto;
import com.jtj.web.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface UserService {

    ResultDto<Object> login(HttpServletRequest request, HttpServletResponse response,
                            String name, String password,Long time);

    ResultDto<Object> logout(HttpServletRequest request, HttpServletResponse response);

    ResultDto<Object> add(User user);

    ResultDto<Object> delete(Long[] ids);

    ResultDto<Object> update(User user) throws AssetException;

    ResultDto<PageDto<User>> getList(UserDto dto);

    ResultDto<User> getUserById(long id);
}
