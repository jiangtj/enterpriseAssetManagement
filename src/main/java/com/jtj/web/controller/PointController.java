package com.jtj.web.controller;

import com.jtj.web.common.exception.AssetException;
import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.PointDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Point;
import com.jtj.web.service.PointService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/2/22.
 */
@RestController
@RequestMapping("/point")
@RequiresRoles("system-administrator-role")
public class PointController {

    @Autowired
    private PointService pointService;

    @PostMapping("/add")
    @RequiresPermissions("point:add")
    public ResultDto<Object> add(Point point){
        return pointService.add(point);
    }

    @PostMapping("/delete")
    public ResultDto<Object> delete(@RequestParam("ids") Long[] ids) throws AssetException {
        return pointService.delete(ids);
    }

    @PostMapping("/update")
    public ResultDto<Object> update(Point type) {
        return pointService.update(type);
    }

    @PostMapping("/getList")
    public ResultDto<PageDto<Point>> getList(PointDto dto){
        return pointService.getList(dto);
    }

    @PostMapping("/getPoint")
    public ResultDto<List<Point>> getPoint(PointDto dto){
        return pointService.getPoint(dto);
    }

    @PostMapping("/getMapByPid")
    public ResultDto<List<KeyValue>> getMapByPid(@RequestParam("pid") Long pid){
        return pointService.getMapByPid(pid);
    }

}
