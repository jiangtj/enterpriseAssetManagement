package com.jtj.web.controller;

import com.jtj.web.common.AssetException;
import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.AssetTypeDto;
import com.jtj.web.dto.MenuDto;
import com.jtj.web.entity.AssetType;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Menu;
import com.jtj.web.service.AssetTypeService;
import com.jtj.web.service.MenuService;
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
@RequestMapping("/assetType")
public class AssetTypeController {

    @Autowired
    private AssetTypeService assetTypeService;

    @PostMapping("/add")
    public ResultDto<Object> add(AssetType type){
        return assetTypeService.add(type);
    }

    @PostMapping("/delete")
    public ResultDto<Object> delete(@RequestParam("ids") Long[] ids) throws AssetException {
        return assetTypeService.delete(ids);
    }

    @PostMapping("/update")
    public ResultDto<Object> update(AssetType type) {
        return assetTypeService.update(type);
    }

    @PostMapping("/getList")
    public ResultDto<PageDto<AssetType>> getList(AssetTypeDto dto){
        return assetTypeService.getList(dto);
    }

    @PostMapping("/getType")
    public ResultDto<List<AssetType>> getType(AssetTypeDto dto){
        return assetTypeService.getType(dto);
    }

    @PostMapping("/getMapById")
    public ResultDto<List<KeyValue>> getMapById(@RequestParam("pid") Long pid){
        return assetTypeService.getMapByPid(pid);
    }

}
