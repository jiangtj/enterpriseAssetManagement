package com.jtj.web.dao;

import com.jtj.web.dto.AssetDto;
import com.jtj.web.dto.AssetTypeDto;
import com.jtj.web.entity.Asset;
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
public interface AssetDao extends BaseDao<Asset,AssetDto>{

}
