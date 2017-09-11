package com.jtj.web.controller;

import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.exception.AssetException;
import com.jtj.web.dto.AssetAndStockTakeNameDto;
import com.jtj.web.dto.AssetDto;
import com.jtj.web.entity.Asset;
import com.jtj.web.entity.AssetOperationRecord;
import com.jtj.web.service.AssetOperationRecordService;
import com.jtj.web.service.AssetService;
import com.jtj.web.service.StockTakeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @RequiresPermissions("asset:add")
    public ResultDto<Object> add(@RequestBody Asset asset){
        return assetService.add(asset);
    }

    @DeleteMapping("/delete")
    @RequiresPermissions("asset:delete")
    public ResultDto<Object> delete(@RequestParam("ids") Long[] ids) throws AssetException {
        return assetService.delete(ids);
    }

    @PutMapping("/update")
    @RequiresPermissions("asset:update")
    public ResultDto<Object> update(@RequestBody Asset asset) {
        return assetService.update(asset);
    }

    @GetMapping("/list")
    @RequiresPermissions("asset:getList")
    public ResultDto<PageDto<Asset>> getList(AssetDto dto){
        return assetService.getList(dto);
    }

    @GetMapping("/getOperationRecordByUuid")
    @RequiresPermissions("asset:record:getByUuid")
    public ResultDto<List<AssetOperationRecord>> getOperationRecordByUuid(@RequestParam String uuid){
        return assetOperationRecordService.getOperationRecordByUuid(uuid);
    }

    @PutMapping("/updateStatus")
    @RequiresPermissions("asset:updateStatus")
    public ResultDto<Object> updateStatus(@RequestBody Map<String,Object> map) {
        return assetService.updateStatus((String) map.get("uuid"),(Integer) map.get("status"),(String) map.get("remark"));
    }

    @PostMapping("/addStockTake")
    @RequiresPermissions("asset:stockTake:add")
    public ResultDto<Object> addStockTake(@RequestBody AssetAndStockTakeNameDto dto) {
        return stockTakeService.addByAsset(dto.getName(),dto.getConditions());
    }

}
