package com.jtj.web.entity;

import com.jtj.web.common.BaseEntity;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/1/31 22:31 End.
 */
public class Role extends BaseEntity {

    private String name;//角色名
    private Integer status;//状态，1：启用，2：不启用
    private List<Permission> permissions;

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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
