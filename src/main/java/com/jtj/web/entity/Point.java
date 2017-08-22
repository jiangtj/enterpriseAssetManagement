package com.jtj.web.entity;

import com.jtj.web.common.BaseEntity;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/4/29 19:25 End.
 */
public class Point extends BaseEntity {

    private String name;
    private Long pid;
    private Integer level;
    private Integer order;
    private Boolean selected;
    private List<Point> nodes;

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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public List<Point> getNodes() {
        return nodes;
    }

    public void setNodes(List<Point> nodes) {
        this.nodes = nodes;
    }
}
