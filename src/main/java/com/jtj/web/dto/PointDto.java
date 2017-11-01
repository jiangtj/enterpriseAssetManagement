package com.jtj.web.dto;

import com.jtj.web.common.BaseDto;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/4/29 19:25 End.
 */
public class PointDto extends BaseDto {

    private String name;
    private Long pid;
    private Integer order;
    private Integer roleId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
