package com.jtj.web.common;

import com.jtj.web.common.utils.JacksonUtils;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/1/22.
 */
public class ResultDto<T> {
    private String code;
    private String title;
    private String message;
    private T object;

    public ResultDto() {
    }

    public ResultDto(ResultCode resultCode) {
        this(resultCode,null);
    }

    public ResultDto(ResultCode resultCode,T object) {
        setResultCode(resultCode);
        this.object = object;
    }

    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.title = resultCode.getMessage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return JacksonUtils.toJson(this);
    }
}
