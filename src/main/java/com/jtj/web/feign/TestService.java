package com.jtj.web.feign;

import com.jtj.web.common.ResultDto;
import com.jtj.web.entity.KeyValue;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/7/24.
 */
@FeignClient(name = "asset3",url = "http://localhost:8081")
@RequestMapping("/public")
public interface TestService {

    @ResponseBody
    @GetMapping("/map/role2")
    ResultDto<List<KeyValue>> getRoleMap();

}
