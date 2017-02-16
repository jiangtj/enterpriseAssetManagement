package com.jtj.web.common;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 0'1'2345
 *    -> 0：标记类型;
 *    -> 1：标记严重级别，0:success,1-3:info,4-6:warn,7-9:error.
 *    -> 2345：该类型序号;
 * 2017/1/22.
 */
public enum ResultCode {
    SUCCESS("000000","成功"),
    UN_USER_INFO("190001","不存在该用户"),
    UNAUTHORIZED("960000","未授权");

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
