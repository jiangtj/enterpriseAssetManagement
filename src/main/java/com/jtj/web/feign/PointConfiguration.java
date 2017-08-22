package com.jtj.web.feign;

import com.jtj.web.common.config.PointConfig;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/8/21 23:33 End.
 */
public class PointConfiguration {
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(PointConfig config) {
        if (config.hasBasicAuth()){
            return new BasicAuthRequestInterceptor(config.getUsername(),config.getPassword());
        }
        return null;
    }
}
