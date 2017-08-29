package com.jtj.web.controller;

import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.exception.AssetException;
import com.jtj.web.dto.UserDto;
import com.jtj.web.entity.User;
import com.jtj.web.service.UserService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:25 End.
 */
@RestController
@RequestMapping("/user")
@RequiresPermissions(value = {"sys:user","system-administrator-permission"},logical = Logical.OR)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResultDto<Object> add(User user){
        return userService.add(user);
    }

    @PostMapping("/delete")
    public ResultDto<Object> delete(@RequestParam("ids") Long[] ids) throws AssetException {
        return userService.delete(ids);
    }

    @PostMapping("/update")
    public ResultDto<Object> update(User user) {
        return userService.update(user);
    }

    @PostMapping("/getList")
    public ResultDto<PageDto<User>> getList(UserDto dto){
        return userService.getList(dto);
    }

}
