package com.jtj.web.service.impl;

import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.PermissionDao;
import com.jtj.web.dao.PointDao;
import com.jtj.web.dao.UserDao;
import com.jtj.web.dto.UserDto;
import com.jtj.web.entity.User;
import com.jtj.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private PointDao pointDao;

    @Override
    public UserDao getRepository() {
        return userDao;
    }

    @Override
    public ResultDto<User> getUserById(long id){
        ResultDto<User> result = new ResultDto<>(ResultCode.SUCCESS);
        result.setObject(userDao.getUserById(id));
        return result;
    }

    @Override
    public ResultDto<PageDto<User>> getList(UserDto dto) {
        ResultDto<PageDto<User>> result = new ResultDto<>(ResultCode.SUCCESS);

        //处理密码
        List<User> userList = userDao.getList(dto);
        userList.forEach(item -> item.setPassword("*******"));

        PageDto<User> page = new PageDto<>();
        page.setList(userList);
        page.setCount(userDao.getNum(dto));
        result.setObject(page);
        return result;
    }

}
