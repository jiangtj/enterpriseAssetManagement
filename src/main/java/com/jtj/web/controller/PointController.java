package com.jtj.web.controller;

import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.exception.AssetException;
import com.jtj.web.dto.PointDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Point;
import com.jtj.web.service.PointService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/2/22.
 */
@Api("网点管理")
@RestController
@RequestMapping("/point")
@RequiresPermissions(value = {"system-administrator-permission","sys:point"},logical = Logical.OR)
public class PointController {

    @Autowired
    private PointService pointService;

    @ApiOperation(value = "添加网点")
    @PostMapping("/add")
    public ResultDto<Object> add(Point point){
        return pointService.add(point);
    }

    @ApiOperation(value = "删除网点")
    @PostMapping("/delete")
    public ResultDto<Object> delete(@RequestParam("ids") Long[] ids) throws AssetException {
        return pointService.delete(ids);
    }

    @ApiOperation(value = "更新网点")
    @PostMapping("/update")
    public ResultDto<Object> update(Point type) {
        return pointService.update(type);
    }

    @ApiOperation(value = "网点列表")
    @PostMapping("/getList")
    public ResultDto<PageDto<Point>> getList(PointDto dto){
        return pointService.getList(dto);
    }

    @ApiOperation(value = "？？")
    @PostMapping("/getPoint")
    public ResultDto<List<Point>> getPoint(PointDto dto){
        return pointService.getPoint(dto);
    }

    @ApiOperation(value = "网点树")
    @GetMapping("/getPointTree")
    public ResultDto<List<Point>> getPointTree(){
        return pointService.getPointTree();
    }

    @ApiOperation(value = "获取网点")
    @PostMapping("/getMapByPid")
    public ResultDto<List<KeyValue>> getMapByPid(@RequestParam("pid") Long pid){
        return pointService.getMapByPid(pid);
    }

    @ApiOperation(value = "获取网点")
    @PostMapping("/getPointByPid")
    public ResultDto<List<Point>> getPointByPid(@RequestParam("pid") Long pid){
        return pointService.getPointByPid(pid);
    }

    @ApiOperation(value = "获取网点")
    @GetMapping("/get/{id}")
    public ResultDto<Point> getPointById(@PathVariable("id") Long id){
        return pointService.getPointById(id);
    }

    @ApiOperation(value = "获取网点")
    @PostMapping("/getQueryRootPoint")
    public ResultDto<List<Point>> getQueryRootPoint(){
        ResultDto<List<Point>> result = new ResultDto<>(ResultCode.SUCCESS);
        result.setObject(pointService.getQueryRootPoint());
        return result;
    }

}
