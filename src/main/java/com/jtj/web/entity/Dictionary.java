package com.jtj.web.entity;

import com.jtj.web.common.BaseEntity;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/4/17.
 */
public class Dictionary extends BaseEntity {

    private String table;
    private String column;
    private String key;
    private String value;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
