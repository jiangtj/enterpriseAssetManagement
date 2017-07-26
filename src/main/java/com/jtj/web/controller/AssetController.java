package com.jtj.web.controller;

import com.jtj.web.common.exception.AssetException;
import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.AssetAndStockTakeNameDto;
import com.jtj.web.dto.AssetDto;
import com.jtj.web.entity.Borrow;
import com.jtj.web.entity.Asset;
import com.jtj.web.entity.AssetOperationRecord;
import com.jtj.web.service.AssetOperationRecordService;
import com.jtj.web.service.AssetService;
import com.jtj.web.service.StockTakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private StockTakeService stockTakeService;
    @Autowired
    private AssetOperationRecordService assetOperationRecordService;

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
        return assetOperationRecordService.getOperationRecordByUuid(uuid);
    }

    @PostMapping("/borrowAsset")
    public ResultDto<Object> borrowAsset(Borrow borrow) {
        return assetService.borrowAsset(borrow);
    }

    @PostMapping("/returnAsset")
    public ResultDto<Object> returnAsset(Borrow borrow) {
        return assetService.returnAsset(borrow);
    }

    @PostMapping("/updateStatus")
    public ResultDto<Object> updateStatus(@RequestParam String uuid,@RequestParam Integer status,@RequestParam String remark) {
        return assetService.updateStatus(uuid,status,remark);
    }

    @PostMapping("/addStockTake")
    public ResultDto<Object> addStockTake(AssetAndStockTakeNameDto dto) {
        return stockTakeService.addByAsset(dto.getName(),dto.getConditions());
    }

}
