package com.jtj.web.service;

import com.jtj.web.common.ResultDto;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface UserService {

    ResultDto<Object> queryById(long id);

    ResultDto<Object> login(HttpServletRequest request, HttpServletResponse response,
                            String name, String password);

    ResultDto<Object> loginOut(HttpServletRequest request, HttpServletResponse response);
}
