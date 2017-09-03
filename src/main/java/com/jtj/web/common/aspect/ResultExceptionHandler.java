package com.jtj.web.common.aspect;

import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.exception.ResultInterf;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/4/9 22:55 End.
 */
@ControllerAdvice
public class ResultExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ResultDto<Object>> handle(Exception e){
        if (e instanceof ResultInterf){
            ResultInterf exception = (ResultInterf) e;
            ResultDto<Object> result = exception.getResult();
            logger.warn(result.toString());
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }

        if (e instanceof UnauthorizedException){
            ResultDto<Object> result = new ResultDto<>(ResultCode.UNAUTHORIZED);
            result.setMessage(e.getMessage());
            logger.warn(result.toString());
            return new ResponseEntity<>(result,HttpStatus.UNAUTHORIZED);
        }

        logger.error("error",e);
        ResultDto<Object> result = new ResultDto<>(ResultCode.UN_KNOWN_ERROR);
        result.setMessage(e.getMessage());
        return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
