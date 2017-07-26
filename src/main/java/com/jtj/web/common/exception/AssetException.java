package com.jtj.web.common.exception;

import com.jtj.web.common.ResultDto;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/4/9 20:04 End.
 */
public class AssetException extends Exception implements ResultInterf {

    private ResultDto<Object> result;

    public AssetException(ResultDto<Object> result){
        super(result.toString());
        this.result = result;
    }

    @Override
    public ResultDto<Object> getResult() {
        return result;
    }
}
