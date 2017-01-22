package com.jtj.web.dto;

import com.jtj.web.common.Constant;
import com.jtj.web.common.ResultCode;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/1/22.
 */
public class ResultDto<T> {
    private String code;
    private String message;
    private T object;

    public ResultDto() {
    }

    public ResultDto(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
}
