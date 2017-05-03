package com.jtj.web.controller;

import com.jtj.web.common.AssetException;
import com.jtj.web.common.PageDto;
import com.jtj.web.common.ResultDto;
import com.jtj.web.dto.AssetDto;
import com.jtj.web.entity.Asset;
import com.jtj.web.entity.AssetOperationRecord;
import com.jtj.web.entity.Borrow;
import com.jtj.web.service.AssetOperationRecordService;
import com.jtj.web.service.AssetService;
import com.jtj.web.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/2/22.
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/getOverall")
    public ResultDto<Object> getOverall(){
        return reportService.getOverall();
    }

    @PostMapping("/getBorrow")
    public ResultDto<Object> getBorrow(@RequestParam Date startTime,@RequestParam Date endTime){
        return reportService.getBorrow(startTime,endTime);
    }

}
