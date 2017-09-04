package com.jtj.web.controller;

import com.jtj.web.common.ResultDto;
import com.jtj.web.service.ReportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/2/22.
 */
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/getOverall")
    @RequiresPermissions("report:getOverall")
    public ResultDto<Object> getOverall(){
        return reportService.getOverall();
    }

    @GetMapping("/getBorrow")
    @RequiresPermissions("report:getBorrow")
    public ResultDto<Object> getBorrow(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startTime,
                                       @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endTime){
        return reportService.getBorrow(startTime,endTime);
    }

}
