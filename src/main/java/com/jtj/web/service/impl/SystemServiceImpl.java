package com.jtj.web.service.impl;

import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.UserDao;
import com.jtj.web.entity.User;
import com.jtj.web.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private UserDao userDao;

    @Override
    public String init(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = userDao.getUserById(1L);
        if (users.size() == 0){
            return "init";
        }
        try {
            response.sendRedirect(request.getContextPath()+"/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "login";
    }
}
