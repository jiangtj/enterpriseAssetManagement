package com.jtj.web.common.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/1/10 21:51 End.
 */

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    /*@Autowired
    private RequestMappingHandlerAdapter handlerAdapter;*/


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(getSecurityInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("welcome");
        registry.addViewController("/welcome").setViewName("welcome");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        super.addViewControllers(registry);
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /*@PostConstruct
    public void genericConversionService() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter
                .getWebBindingInitializer();
        if (initializer.getConversionService() != null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer
                    .getConversionService();
            //string to date
            genericConversionService.addConverter(new StringToDateConverter());
        }

    }*/


}
