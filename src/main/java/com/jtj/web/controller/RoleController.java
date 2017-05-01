package com.jtj.web.controller;

import com.jtj.web.common.AssetException;
import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.RoleDto;
import com.jtj.web.dto.UserDto;
import com.jtj.web.entity.Role;
import com.jtj.web.entity.User;
import com.jtj.web.service.RoleService;
import com.jtj.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/2/22.
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public ResultDto<Object> add(Role role){
        return roleService.add(role);
    }

    @PostMapping("/delete")
    public ResultDto<Object> delete(@RequestParam("ids") Long[] ids) throws AssetException {
        return roleService.delete(ids);
    }

    @PostMapping("/update")
    public ResultDto<Object> update(Role role) {
        return roleService.update(role);
    }

    @PostMapping("/getList")
    public ResultDto<PageDto<Role>> getList(RoleDto dto){
        return roleService.getList(dto);
    }

    @PostMapping("/updatePermission")
    public ResultDto<Object> updatePermission(@RequestParam Long roleId,@RequestParam Long[] menuIds){
        return roleService.updatePermission(roleId,menuIds);
    }

    @PostMapping("/updatePoint")
    public ResultDto<Object> updatePoint(@RequestParam Long roleId,@RequestParam Long[] pointIds){
        return roleService.updatePoint(roleId,pointIds);
    }

}
