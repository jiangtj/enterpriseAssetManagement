package com.jtj.web.controller;

import com.jtj.web.common.exception.AssetException;
import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.StockTakeDto;
import com.jtj.web.dto.StockTakeItemDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.StockTake;
import com.jtj.web.entity.StockTakeItem;
import com.jtj.web.service.StockTakeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/2/22.
 */
@RestController
@RequestMapping("/stockTake")
public class StockTakeController {

    @Autowired
    private StockTakeService stockTakeService;

    @PostMapping("/add")
    @RequiresPermissions("stockTake:add")
    public ResultDto<Object> add(StockTake stockTake){
        return stockTakeService.add(stockTake);
    }

    @PostMapping("/delete")
    @RequiresPermissions("stockTake:delete")
    public ResultDto<Object> delete(@RequestParam("ids") Long[] ids) throws AssetException {
        return stockTakeService.delete(ids);
    }

    @PostMapping("/update")
    @RequiresPermissions("stockTake:update")
    public ResultDto<Object> update(StockTake stockTake) {
        return stockTakeService.update(stockTake);
    }

    @PostMapping("/getList")
    @RequiresPermissions("stockTake:getList")
    public ResultDto<PageDto<StockTake>> getList(StockTakeDto dto){
        return stockTakeService.getList(dto);
    }

    @PostMapping("/handle")
    @RequiresPermissions("stockTake:handle")
    public ResultDto<Object> handleItem(StockTakeItem item) {
        return stockTakeService.handleItem(item);
    }

    @GetMapping("/getAvailableMap")
    public ResultDto<List<KeyValue>> getAvailableMap(){
        return stockTakeService.getAvailableMap();
    }

    @PostMapping("/updateAmount")
    @RequiresPermissions("stockTake:updateAmount")
    public ResultDto<StockTake> updateAmount(@RequestParam Long id) {
        return stockTakeService.updateAmount(id);
    }

    @PostMapping("/getItemList")
    @RequiresPermissions("stockTake:item:getList")
    public ResultDto<PageDto<StockTakeItem>> getItemList(StockTakeItemDto dto) {
        return stockTakeService.getItemList(dto);
    }

    @PostMapping("/updateToAbnormal")
    @RequiresPermissions("stockTake:updateToAbnormal")
    public ResultDto<Object> updateToAbnormal(@RequestParam("id") Long id) throws AssetException {
        return stockTakeService.updateToAbnormal(id);
    }

    @PostMapping("/close")
    @RequiresPermissions("stockTake:close")
    public ResultDto<StockTake> close(@RequestParam("id") Long id) throws AssetException {
        return stockTakeService.close(id);
    }

}
