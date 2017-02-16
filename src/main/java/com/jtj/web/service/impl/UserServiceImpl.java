package com.jtj.web.service.impl;

import com.jtj.web.common.Constant;
import com.jtj.web.common.ResultCode;
import com.jtj.web.dao.UserDao;
import com.jtj.web.common.ResultDto;
import com.jtj.web.entity.User;
import com.jtj.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ResultDto<Object> queryById(long id){
        ResultDto<Object> result = new ResultDto<>(ResultCode.SUCCESS);
        result.setObject(userDao.queryById(id));
        return result;
    }

    @Override
    public ResultDto<Object> login(HttpServletRequest request, HttpServletResponse response,
                                   String name, String password) {
        ResultDto<Object> result = new ResultDto<>();
        User user = userDao.login(name,password);
        //不存在该用户
        if (user == null) {
            result.setResultCode(ResultCode.UN_USER_INFO);
            result.setMessage("请重新检测帐号是否输错！");
            return result;
        }

        //用户信息放入session
        HttpSession session = request.getSession();
        session.setAttribute(Constant.SESSION_USER,user);

        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    @Override
    public ResultDto<Object> loginOut(HttpServletRequest request, HttpServletResponse response) {
        ResultDto<Object> result = new ResultDto<>();
        //清除session
        HttpSession session = request.getSession();
        session.removeAttribute(Constant.SESSION_USER);
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

}
