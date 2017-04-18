package com.jtj.web.controller;

import com.jtj.web.common.AssetException;
import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.MenuDto;
import com.jtj.web.dto.PermissionDto;
import com.jtj.web.entity.Menu;
import com.jtj.web.entity.Permission;
import com.jtj.web.service.MenuService;
import com.jtj.web.service.PermissionService;
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
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/add")
    public ResultDto<Object> add(Menu menu){
        return menuService.add(menu);
    }

    @PostMapping("/delete")
    public ResultDto<Object> delete(@RequestParam("ids") Long[] ids) throws AssetException {
        return menuService.delete(ids);
    }

    @PostMapping("/update")
    public ResultDto<Object> update(Menu menu) {
        return menuService.update(menu);
    }

    @PostMapping("/getList")
    public ResultDto<PageDto<Menu>> getList(MenuDto dto){
        return menuService.getList(dto);
    }

}
