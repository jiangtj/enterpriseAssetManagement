package com.jtj.web.service.impl;

import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.utils.DateUtils;
import com.jtj.web.dao.ReportDao;
import com.jtj.web.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/3/15.
 */
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportDao reportDao;

    @Override
    public ResultDto<Object> getOverall() {
        ResultDto<Object> result = new ResultDto<>(ResultCode.SUCCESS);
        result.setObject(reportDao.getOverall());
        return result;
    }

    @Override
    public ResultDto<Object> getBorrow(Date startTime, Date endTime) {
        ResultDto<Object> result = new ResultDto<>(ResultCode.SUCCESS);

        Integer days = DateUtils.getIntervalDays(startTime, endTime);
        Date tempTime = startTime;
        List<Date> dateList = new ArrayList<>();
        dateList.add(tempTime);
        for (int i =0;i<days;i++){
            tempTime = DateUtils.addDay(tempTime,1);
            dateList.add(tempTime);
        }
        Date endTimeNextDay = DateUtils.addDay(endTime,1);

        result.setObject(reportDao.getBorrow(startTime,endTimeNextDay,dateList));
        return result;
    }
}
