package com.jtj.web.dto;

import com.jtj.web.common.BaseDto;

import java.math.BigDecimal;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/2/22.
 */
public class AssetDto extends BaseDto {

    private String uuid;
    private String customsId;
    private String name;
    private BigDecimal price;
    private Integer status;
    private Integer assetsTypeId;
    private Long pointId;

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