package com.jtj.web.dto;

import com.jtj.web.common.BaseDto;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/1/31 22:31 End.
 */
public class RoleDto extends BaseDto {

    private String name;//角色名
    private Integer status;//状态，1：启用，2：不启用

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
