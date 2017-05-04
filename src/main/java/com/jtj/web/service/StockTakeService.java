package com.jtj.web.service;

import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.AssetDto;
import com.jtj.web.dto.StockTakeDto;
import com.jtj.web.dto.StockTakeItemDto;
import com.jtj.web.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface StockTakeService extends BaseService<StockTake,StockTakeDto> {

    ResultDto<Object> addByAsset(String name, AssetDto dto);

    ResultDto<Object> handleItem(StockTakeItem item);

    ResultDto<List<KeyValue>> getAvailableMap();

    ResultDto<StockTake> updateAmount(Long id);

    ResultDto<PageDto<StockTakeItem>> getItemList(StockTakeItemDto dto);

    ResultDto<Object> updateToAbnormal(Long id);
}
