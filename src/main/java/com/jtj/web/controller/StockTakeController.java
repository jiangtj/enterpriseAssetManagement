package com.jtj.web.controller;

import com.jtj.web.common.AssetException;
import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.PermissionDto;
import com.jtj.web.dto.StockTakeDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Permission;
import com.jtj.web.entity.StockTake;
import com.jtj.web.entity.StockTakeItem;
import com.jtj.web.service.PermissionService;
import com.jtj.web.service.StockTakeService;
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
    public ResultDto<Object> add(StockTake stockTake){
        return stockTakeService.add(stockTake);
    }

    @PostMapping("/delete")
    public ResultDto<Object> delete(@RequestParam("ids") Long[] ids) throws AssetException {
        return stockTakeService.delete(ids);
    }

    @PostMapping("/update")
    public ResultDto<Object> update(StockTake stockTake) {
        return stockTakeService.update(stockTake);
    }

    @PostMapping("/getList")
    public ResultDto<PageDto<StockTake>> getList(StockTakeDto dto){
        return stockTakeService.getList(dto);
    }

    @PostMapping("/handle")
    public ResultDto<Object> handleItem(StockTakeItem item) {
        return stockTakeService.handleItem(item);
    }

}
