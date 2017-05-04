package com.jtj.web.service;

import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.AssetDto;
import com.jtj.web.dto.StockTakeDto;
import com.jtj.web.entity.Asset;
import com.jtj.web.entity.Borrow;
import com.jtj.web.entity.StockTake;
import com.jtj.web.entity.StockTakeItem;
import org.springframework.stereotype.Service;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface StockTakeService extends BaseService<StockTake,StockTakeDto> {

    ResultDto<Object> addByAsset(String name, AssetDto dto);

    ResultDto<Object> handleItem(StockTakeItem item);
}
