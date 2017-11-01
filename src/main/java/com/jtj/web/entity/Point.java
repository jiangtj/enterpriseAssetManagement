package com.jtj.web.entity;

import com.jtj.web.service.base.TreeEntity;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/4/29 19:25 End.
 */
public class Point extends TreeEntity<Point> {

    private String name;
    private Integer order;
    private Boolean selected;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
