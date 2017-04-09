package com.jtj.web.common;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/4/9 20:04 End.
 */
public class AssetException extends Exception {

    private ResultDto<Object> result;

    public AssetException(ResultDto<Object> result){
        super(result.toString());
        this.result = result;
    }

    public ResultDto<Object> getResult() {
        return result;
    }
}
