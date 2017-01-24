package com.jtj.web.service.impl;

import com.jtj.web.common.ResultCode;
import com.jtj.web.dao.UserDao;
import com.jtj.web.common.ResultDto;
import com.jtj.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
