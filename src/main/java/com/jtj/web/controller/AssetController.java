package com.jtj.web.controller;

import com.jtj.web.common.AssetException;
import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.AssetDto;
import com.jtj.web.dto.AssetTypeDto;
import com.jtj.web.entity.Asset;
import com.jtj.web.entity.AssetOperationRecord;
import com.jtj.web.entity.AssetType;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.service.AssetService;
import com.jtj.web.service.AssetTypeService;
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
@RequestMapping("/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping("/add")
    public ResultDto<Object> add(Asset asset){
        return assetService.add(asset);
    }

    @PostMapping("/delete")
    public ResultDto<Object> delete(@RequestParam("ids") Long[] ids) throws AssetException {
        return assetService.delete(ids);
    }

    @PostMapping("/update")
    public ResultDto<Object> update(Asset asset) {
        return assetService.update(asset);
    }

    @PostMapping("/getList")
    public ResultDto<PageDto<Asset>> getList(AssetDto dto){
        return assetService.getList(dto);
    }

    @PostMapping("/getOperationRecordByUuid")
    public ResultDto<List<AssetOperationRecord>> getOperationRecordByUuid(String uuid){
        return assetService.getOperationRecordByUuid(uuid);
    }

    @PostMapping("/borrowAsset")
    public ResultDto<Object> borrowAsset(AssetDto dto) {
        return assetService.borrowAsset(dto);
    }

    @PostMapping("/returnAsset")
    public ResultDto<Object> returnAsset(AssetDto dto) {
        return assetService.returnAsset(dto);
    }

}
