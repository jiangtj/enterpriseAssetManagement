package com.jtj.web.dao;

import com.jtj.web.dto.AssetDto;
import com.jtj.web.dto.StockTakeDto;
import com.jtj.web.dto.StockTakeItemDto;
import com.jtj.web.entity.Asset;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.StockTake;
import com.jtj.web.entity.StockTakeItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Mapper
@Component
public interface StockTakeDao extends BaseDao<StockTake,StockTakeDto>{

    int addItem(@Param("stockTakeId") Long stockTakeId,@Param("assetDto") AssetDto dto);

    List<StockTakeItem> getItemList(StockTakeItemDto dto);

    int getItemNum(StockTakeItemDto dto);

    int updateItemStatus(@Param("stockTakeId") Long stockTakeId,@Param("uuid") String uuid,
                         @Param("status") Integer status);

    int updateItemStatusById(@Param("id") Long id,@Param("status") Integer status);

    List<KeyValue> getAvailableMap();

    int updateAmount(@Param("id") Long id);

    StockTake getById(@Param("id") Long id);

    int updateHandlingToAbnormalByStockTakeId(@Param("stockTakeId") Long id);
}
