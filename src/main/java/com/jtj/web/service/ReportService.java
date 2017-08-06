package com.jtj.web.service;

import com.jtj.web.common.ResultDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface ReportService {

    ResultDto<Object> getOverall();

    ResultDto<Object> getBorrow(LocalDate startTime, LocalDate endTime);
}
