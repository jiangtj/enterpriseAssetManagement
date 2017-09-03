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
    SUCCESS_GET("000001","查询成功"),
    SUCCESS_PUT("000002","修改成功"),
    SUCCESS_POST("000003","添加成功"),
    SUCCESS_DELETE("000004","删除成功"),
    SUCCESS_OPERATE("000005","操作成功"),
    USER_INFO_NON_EXISTENT("150001","不存在该用户"),
    USER_TIME_ERROR("190002","请求时间异常"),
    USER_PASSWORD_ERROR("190003","密码错误"),
    ASSET_NON_EXISTENT("260001","不能存在该资产"),
    ASSET_NOT_ONLY("260002","当前条件下，资产不唯一"),
    UNAUTHORIZED("960000","未授权"),
    OPERATE_FAIL("980001","操作失败"),
    NOT_DELETE_ROOT("970002","根节点不能删除"),
    NOT_DELETE_USED("970003","不能删除被占用的资源"),
    NOT_FOUND("970003","资源不存在"),
    UN_KNOWN_ERROR("999999","未知错误");

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
