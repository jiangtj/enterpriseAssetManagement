package com.jtj.web.feign;

import com.jtj.web.common.ResultDto;
import com.jtj.web.common.utils.JacksonUtils;
import com.jtj.web.entity.KeyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/7/24.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService service;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/getData")
    public Object getData(){
        //同步
        return service.getRoleMap();
    }
    @GetMapping("/getData2")
    public Object getData2(){
        //异步
        CompletableFuture<ResultDto<List<KeyValue>>> a = CompletableFuture.supplyAsync(() -> {
            ResultDto<List<KeyValue>> result = service.getRoleMap();
            logger.error(JacksonUtils.toJson(result));
            return result;
        });
        return "success";
    }

}
