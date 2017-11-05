package com.jtj.web.dao;

import com.jtj.web.dto.AssetTypeDto;
import com.jtj.web.entity.AssetType;
import com.jtj.web.entity.KeyValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Mapper
@Component
public interface AssetTypeDao extends BaseDao<AssetType,AssetTypeDto>{

    List<AssetType> getType(AssetTypeDto dto);

    List<KeyValue> getMapByPid(@Param("pid") Long pid);

    AssetType getById(@Param("id") Long id);

    List<AssetType> getAll();
}
