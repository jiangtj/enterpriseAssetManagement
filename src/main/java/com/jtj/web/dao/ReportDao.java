package com.jtj.web.dao;

import com.jtj.web.dto.AssetDto;
import com.jtj.web.entity.Asset;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Mapper
@Component
public interface ReportDao{

    Map<String,Object> getOverall();

    List<Map<String,Object>> getBorrow(@Param("startTime") Date startTime,@Param("endTime") Date endTime,
                                       @Param("dateList") List<Date> dateList);
}
