package com.jtj.web.dto;

import com.jtj.web.common.BaseDto;

import java.util.Date;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/5/4.
 */
public class AssetAndStockTakeNameDto extends BaseDto {

    private String name;
    private AssetDto conditions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AssetDto getConditions() {
        return conditions;
    }

    public void setConditions(AssetDto conditions) {
        this.conditions = conditions;
    }
}
