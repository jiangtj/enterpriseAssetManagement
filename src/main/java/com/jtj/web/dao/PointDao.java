package com.jtj.web.dao;

import com.jtj.web.dto.AssetTypeDto;
import com.jtj.web.dto.PointDto;
import com.jtj.web.entity.AssetType;
import com.jtj.web.entity.KeyValue;
import com.jtj.web.entity.Point;
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
public interface PointDao extends BaseDao<Point,PointDto>{

    List<Point> getPoint(PointDto dto);

    List<KeyValue> getMapByPid(@Param("pid") Long pid);

    Point getById(@Param("id") Long id);

    List<Point> getAuthorizedPoint(@Param("userId") Long userId,@Param("roleId") Long roleId);

    List<Point> getPointByIds(@Param("ids") List<Long> pointIdList);

    List<Point> getPointByPids(@Param("pids") List<Long> pointIdList);
}
