package com.jtj.web.dao;

import com.jtj.web.dto.AssetDto;
import com.jtj.web.dto.StockTakeDto;
import com.jtj.web.entity.Asset;
import com.jtj.web.entity.StockTake;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Mapper
@Component
public interface StockTakeDao extends BaseDao<StockTake,StockTakeDto>{

    int addItem(@Param("stockTakeId") Long stockTakeId,@Param("assetDto") AssetDto dto);

}
