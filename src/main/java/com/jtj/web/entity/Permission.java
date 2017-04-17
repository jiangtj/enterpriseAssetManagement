package com.jtj.web.entity;

import com.jtj.web.common.BaseEntity;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/4/17 21:52 End.
 */
public class Permission extends BaseEntity {

    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
