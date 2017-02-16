package com.jtj.web.controller;

import com.jtj.web.common.ResultDto;
import com.jtj.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 公共接口控制层，该层接口无须权限
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/1/24 22:37 End.
 */
@Controller
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/login")
    public ResultDto<Object> login(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam("name") String name, @RequestParam("password") String password){

        return userService.login(request,response,name,password);
    }

    @ResponseBody
    @PostMapping("/loginOut")
    public ResultDto<Object> loginOut(HttpServletRequest request, HttpServletResponse response){
        return userService.loginOut(request,response);
    }

}
