package com.jtj.web.service.base;

import com.jtj.web.common.BaseEntity;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/10/15 23:03 End.
 */
public class TreeEntity extends BaseEntity {

    private Long pid;
    private List<TreeEntity> nodes;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public List<TreeEntity> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeEntity> nodes) {
        this.nodes = nodes;
    }
}
