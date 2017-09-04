package com.jtj.web.controller;

import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.UsernamePasswordTokenDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.service.PointService;
import com.jtj.web.service.RoleService;
import com.jtj.web.service.SystemService;
import com.jtj.web.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    private RoleService roleService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private PointService pointService;

    @ApiOperation("用户登录")
    @ResponseBody
    @PostMapping("/login")
    public ResultDto<Object> login(@RequestBody UsernamePasswordTokenDto token){
        Subject subject = SecurityUtils.getSubject();
        token.setRememberMe(false);
        subject.login(token);

        //触发获取权限
        SecurityUtils.getSubject().hasRole("doGetAuthorizationInfo");

        ResultDto<Object> result = new ResultDto<>();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    @ApiOperation("用户登出")
    @ResponseBody
    @PostMapping("/logout")
    public ResultDto<Object> logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        ResultDto<Object> result = new ResultDto<>();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    @ApiOperation("角色列表")
    @ResponseBody
    @GetMapping("/map/role")
    public ResultDto<List<KeyValue>> getRoleMap(){
        return roleService.getRoleMap();
    }

    @ApiOperation("字典列表")
    @ResponseBody
    @GetMapping("/map/dictionary/{table}/{column}")
    public ResultDto<List<KeyValue>> getDictionaryMap(@PathVariable("table") String table,
                                                      @PathVariable("column") String column){
        return systemService.getDictionaryMap(table, column);
    }

}
