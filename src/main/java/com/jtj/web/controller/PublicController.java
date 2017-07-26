package com.jtj.web.controller;

import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.MenuDto;
import com.jtj.web.dto.UsernamePasswordTokenDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Menu;
import com.jtj.web.entity.Point;
import com.jtj.web.service.*;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    private MenuService menuService;
    @Autowired
    private PointService pointService;

    @ApiOperation(value="获取用户列表", notes="xxxx")
    @ResponseBody
    @PostMapping("/login")
    public ResultDto<Object> login(HttpServletRequest request, HttpServletResponse response, @RequestParam("name") String name,
                                   @RequestParam("password") String password,@RequestParam("time") Long time){
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordTokenDto(name,password,time));

        ResultDto<Object> result = new ResultDto<>();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    @ResponseBody
    @PostMapping("/logout")
    public ResultDto<Object> loginOut(HttpServletRequest request, HttpServletResponse response){
        return userService.logout(request,response);
    }

    @ResponseBody
    @GetMapping("/map/role")
    public ResultDto<List<KeyValue>> getRoleMap(){
        return roleService.getRoleMap();
    }

    @ResponseBody
    @GetMapping("/map/dictionary/{table}/{column}")
    public ResultDto<List<KeyValue>> getDictionaryMap(@PathVariable("table") String table,
                                                      @PathVariable("column") String column){
        return systemService.getDictionaryMap(table, column);
    }

    @ResponseBody
    @PostMapping("/getMenu")
    public ResultDto<List<Menu>> getMenu(MenuDto dto){
        return menuService.getMenu(dto);
    }

    @ResponseBody
    @PostMapping("/getPoint")
    public ResultDto<List<Point>> getPoint(HttpSession session){
        return pointService.getPublicPoint(session);
    }

}
