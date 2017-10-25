package com.jtj.web.service;

import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.StockTakeDao;
import com.jtj.web.dto.AssetDto;
import com.jtj.web.dto.StockTakeDto;
import com.jtj.web.dto.StockTakeItemDto;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.StockTake;
import com.jtj.web.entity.StockTakeItem;
import com.jtj.web.service.base.CurdService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface StockTakeService extends CurdService<StockTake,StockTakeDto,StockTakeDao> {

    ResultDto<Object> addByAsset(String name, AssetDto dto);

    ResultDto<Object> handleItem(StockTakeItem item);

    ResultDto<List<KeyValue>> getAvailableMap();

    ResultDto<StockTake> updateAmount(Long id);

    ResultDto<PageDto<StockTakeItem>> getItemList(StockTakeItemDto dto);

    ResultDto<Object> updateToAbnormal(Long id);

    ResultDto<StockTake> close(Long id);
}
