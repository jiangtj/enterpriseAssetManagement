package com.jtj.web.dto;

import com.jtj.web.common.BaseDto;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/4/17 21:52 End.
 */
public class PermissionDto extends BaseDto {

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
