package com.jtj.web.common;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/1/22.
 */
public enum ResultCode {
    SUCCESS("000000","成功"),
    UN_USER_INFO("100001","不存在该用户"),
    UNAUTHORIZED("900000","未授权");

    private String code;
    private String message;

    ResultCode(String code,String message){
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
