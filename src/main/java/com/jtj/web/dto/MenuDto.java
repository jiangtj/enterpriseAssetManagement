package com.jtj.web.dto;

import com.jtj.web.common.BaseDto;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/4/18 21:06 End.
 */
public class MenuDto extends BaseDto {

    private Long id;
    private String name;
    private Integer level;
    private Long pid;
    private Integer order;
    private Integer isMenu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }
}
