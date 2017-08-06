package com.jtj.web.service.impl;

import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dao.ReportDao;
import com.jtj.web.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
    public ResultDto<Object> getBorrow(LocalDate start, LocalDate end) {
        ResultDto<Object> result = new ResultDto<>(ResultCode.SUCCESS);
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate temp = start;
        while (end.isAfter(temp)){
            temp = temp.plusDays(1);
            dateList.add(temp);
        }
        result.setObject(reportDao.getBorrow(start,end.plusDays(1),dateList));
        return result;
    }
}
