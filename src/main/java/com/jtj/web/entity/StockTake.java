package com.jtj.web.entity;

import com.jtj.web.common.BaseEntity;

import java.util.Date;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/5/4.
 */
public class StockTake extends BaseEntity {

    private String name;
    private Long userId;
    private Date endTime;
    private Integer allAmount;
    private Integer handlingAmount;
    private Integer normalAmount;
    private Integer abnormalAmount;
    private Integer status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getAllAmount() {
        return allAmount;
    }

    public void setAllAmount(Integer allAmount) {
        this.allAmount = allAmount;
    }

    public Integer getHandlingAmount() {
        return handlingAmount;
    }

    public void setHandlingAmount(Integer handlingAmount) {
        this.handlingAmount = handlingAmount;
    }

    public Integer getNormalAmount() {
        return normalAmount;
    }

    public void setNormalAmount(Integer normalAmount) {
        this.normalAmount = normalAmount;
    }

    public Integer getAbnormalAmount() {
        return abnormalAmount;
    }

    public void setAbnormalAmount(Integer abnormalAmount) {
        this.abnormalAmount = abnormalAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
