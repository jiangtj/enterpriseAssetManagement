package com.jtj.web.controller;

import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.exception.AssetException;
import com.jtj.web.dto.AssetTypeDto;
import com.jtj.web.entity.AssetType;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.service.AssetTypeService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/2/22.
 */
@RestController
@RequestMapping("/assetType")
public class AssetTypeController {

    @Autowired
    private AssetTypeService assetTypeService;

    @PostMapping("/add")
    @RequiresPermissions("sys:assetType:add")
    public ResultDto<Object> add(@RequestBody AssetType type){
        return assetTypeService.add(type);
    }

    @DeleteMapping("/delete")
    @RequiresPermissions("sys:assetType:delete")
    public ResultDto<Object> delete(@RequestParam("ids") Long[] ids) throws AssetException {
        return assetTypeService.delete(ids);
    }

    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("sys:assetType:delete")
    public ResultDto<Object> deleteById(@PathVariable("id") Long id) throws AssetException {
        return assetTypeService.deleteById(id);
    }

    @PutMapping("/update")
    @RequiresPermissions("sys:assetType:update")
    public ResultDto<Object> update(@RequestBody AssetType type) {
        return assetTypeService.update(type);
    }

    @GetMapping("/list")
    @RequiresPermissions("sys:assetType:getList")
    public ResultDto<PageDto<AssetType>> getList(AssetTypeDto dto){
        return assetTypeService.getList(dto);
    }

    @GetMapping("/getType")
    @RequiresPermissions("sys:assetType:getType")
    public ResultDto<List<AssetType>> getType(AssetTypeDto dto){
        return assetTypeService.getType(dto);
    }

    @GetMapping("/getMapByPid")
    //todo map permissions
    public ResultDto<List<KeyValue>> getMapByPid(@RequestParam("pid") Long pid){
        return assetTypeService.getMapByPid(pid);
    }

    @ApiOperation(value = "刷新缓存数据")
    @PostMapping("/refresh")
    public ResultDto<List<Object>> refresh(){
        assetTypeService.refreshTreeData();
        return new ResultDto<>(ResultCode.SUCCESS);
    }

    @GetMapping("/tree")
    public ResultDto<List<AssetType>> getPointTree(){
        return assetTypeService.getResultTree();
    }

}
