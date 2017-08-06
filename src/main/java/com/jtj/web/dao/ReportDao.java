package com.jtj.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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

    List<Map<String,Object>> getBorrow(@Param("startTime") LocalDate startTime, @Param("endTime") LocalDate endTime,
                                       @Param("dateList") List<LocalDate> dateList);
}
