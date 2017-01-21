package com.jtj.web.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/1/10 21:34 End.
 */
@Aspect
public class auth {

    @Pointcut("execution(public * com.jtj.web.controller.*.*(..))")
    public void authPoint(){}

    @Before("authPoint()")
    public void beforeAuth(JoinPoint joinPoint){
    }
}
