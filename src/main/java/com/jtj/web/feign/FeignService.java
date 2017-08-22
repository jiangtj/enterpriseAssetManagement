package com.jtj.web.feign;

import com.jtj.web.common.config.PointConfig;
import com.jtj.web.entity.Point;
import feign.Client;
import feign.auth.BasicAuthRequestInterceptor;
import feign.hystrix.HystrixFeign;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/8/21 21:55 End.
 */
//@Component
@Deprecated
public class FeignService {

    private PointClient pointClient;

    @Autowired
    public FeignService(Client client,PointConfig pointConfig,PointClientImpl pointClientFallback) {
        HystrixFeign.Builder builder = HystrixFeign.builder().client(client);
        if (pointConfig.hasBasicAuth()){
            builder.requestInterceptor(new BasicAuthRequestInterceptor(pointConfig.getUsername(),pointConfig.getPassword()));
        }
        this.pointClient = builder.target(PointClient.class,pointConfig.getName(),pointClientFallback);
    }

    public List<Point> getPoint() {
        return pointClient.getPoint();
    }
}
