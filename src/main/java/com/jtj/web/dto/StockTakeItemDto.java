package com.jtj.web.dto;

import com.jtj.web.common.BaseDto;
import com.jtj.web.common.BaseEntity;

import java.math.BigDecimal;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/2/22.
 */
public class StockTakeItemDto extends BaseDto {

    private Long stockTakeId;
    private String uuid;
    private String customsId;
    private String name;
    private BigDecimal price;
    private Integer status;
    private String statusName;
    private Integer assetsTypeId;
    private Long pointId;

    public Long getStockTakeId() {
        return stockTakeId;
    }

    public void setStockTakeId(Long stockTakeId) {
        this.stockTakeId = stockTakeId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCustomsId() {
        return customsId;
    }

    public void setCustomsId(String customsId) {
        this.customsId = customsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getAssetsTypeId() {
        return assetsTypeId;
    }

    public void setAssetsTypeId(Integer assetsTypeId) {
        this.assetsTypeId = assetsTypeId;
    }

    public Long getPointId() {
        return pointId;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }
}
