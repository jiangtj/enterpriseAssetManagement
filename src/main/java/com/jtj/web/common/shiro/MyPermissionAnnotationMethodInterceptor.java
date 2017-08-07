package com.jtj.web.common.shiro;

import org.apache.shiro.aop.AnnotationResolver;
import org.apache.shiro.authz.aop.AuthorizingAnnotationMethodInterceptor;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/8/7.
 */
public class MyPermissionAnnotationMethodInterceptor extends AuthorizingAnnotationMethodInterceptor {
    public MyPermissionAnnotationMethodInterceptor() {
        super(new MyPermissionAnnotationHandler());
    }

    public MyPermissionAnnotationMethodInterceptor(AnnotationResolver resolver) {
        super(new MyPermissionAnnotationHandler(), resolver);
    }
}
