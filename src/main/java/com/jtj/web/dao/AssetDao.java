package com.jtj.web.dao;

import com.jtj.web.dto.AssetDto;
import com.jtj.web.entity.Asset;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Mapper
@Component
public interface AssetDao extends BaseDao<Asset,AssetDto>{

    int updateAssetStatus(@Param("uuid") String uuid,@Param("status") Integer status);

    int updateToNewPoint(@Param("from") Long from,@Param("to") Long to);
}
