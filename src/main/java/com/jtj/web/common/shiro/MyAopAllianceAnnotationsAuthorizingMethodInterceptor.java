package com.jtj.web.common.shiro;

import org.apache.shiro.aop.AnnotationResolver;
import org.apache.shiro.authz.aop.*;
import org.apache.shiro.spring.aop.SpringAnnotationResolver;
import org.apache.shiro.spring.security.interceptor.AopAllianceAnnotationsAuthorizingMethodInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/8/7.
 */
public class MyAopAllianceAnnotationsAuthorizingMethodInterceptor
        extends AopAllianceAnnotationsAuthorizingMethodInterceptor {
    public MyAopAllianceAnnotationsAuthorizingMethodInterceptor() {
        List<AuthorizingAnnotationMethodInterceptor> interceptors = new ArrayList(5);
        AnnotationResolver resolver = new SpringAnnotationResolver();
        interceptors.add(new RoleAnnotationMethodInterceptor(resolver));
        interceptors.add(new MyPermissionAnnotationMethodInterceptor(resolver));
        interceptors.add(new AuthenticatedAnnotationMethodInterceptor(resolver));
        interceptors.add(new UserAnnotationMethodInterceptor(resolver));
        interceptors.add(new GuestAnnotationMethodInterceptor(resolver));
        this.setMethodInterceptors(interceptors);
    }
}
