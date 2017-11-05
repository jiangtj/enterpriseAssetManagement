package com.jtj.web.dao;

import com.jtj.web.dto.PointDto;
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

    List<KeyValue> getMapByPid(@Param("pid") Long pid);

    List<Point> getAllPoint();
}
