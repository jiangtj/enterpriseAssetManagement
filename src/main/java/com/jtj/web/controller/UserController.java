package com.jtj.web.controller;

import com.jtj.web.dto.ResultDto;
import com.jtj.web.entity.User;
import com.jtj.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:25 End.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public ResultDto<Object> queryById(@PathVariable("id") long id){
        return userService.queryById(id);
    }
}
