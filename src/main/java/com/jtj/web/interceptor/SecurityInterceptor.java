package com.jtj.web.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/1/10 23:31 End.
 */
public class SecurityInterceptor implements HandlerInterceptor{

    private final static String separator = ",";

    @Value("${interceptor.ignore.suffix}")
    private String ignoreSuffixString;

    @Value("${interceptor.ignore.url}")
    private String ignoreUrlString;

    private String[] ignoreSuffixArr;

    private String[] ignoreUrlArr;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        initIgnoreArr();

        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        PrintWriter out = httpServletResponse.getWriter();
        out.println(
                "{\"1\":\"2\"}");
        out.flush();
        out.close();

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private void initIgnoreArr() {
        if (ignoreSuffixArr == null){
            ignoreSuffixArr = ignoreSuffixString.split(separator);
        }
        if (ignoreUrlArr == null){
            ignoreUrlArr = ignoreUrlString.split(separator);
        }
    }
}
