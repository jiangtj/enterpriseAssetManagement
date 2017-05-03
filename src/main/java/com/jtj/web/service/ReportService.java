package com.jtj.web.service;

import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.AssetDto;
import com.jtj.web.entity.Asset;
import com.jtj.web.entity.Borrow;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2016/12/23 23:26 End.
 */
@Service
public interface ReportService {

    ResultDto<Object> getOverall();

    ResultDto<Object> getBorrow(Date startTime, Date endTime);
}
