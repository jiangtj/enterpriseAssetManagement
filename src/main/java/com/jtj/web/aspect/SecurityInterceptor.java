package com.jtj.web.aspect;

import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
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

    @Value("${interceptor.auth.url}")
    private String authUrlString;
    @Value("${interceptor.ignore.url}")
    private String ignoreUrlString;
    @Value("${interceptor.ignore.suffix}")
    private String ignoreSuffixString;

    private String[] authUrlArr;
    private String[] ignoreUrlArr;
    private String[] ignoreSuffixArr;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //初始化列表
        initAuthOrIgnoreArr();

        //过滤验证url
        String servletPath = httpServletRequest.getServletPath();
        if (mathAuthUrl(servletPath)) return true;

        httpServletResponse.setContentType("application/json");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        PrintWriter out = httpServletResponse.getWriter();
        //out.println("{\"1\":\"2\"}");
        ResultDto<Object> result = new ResultDto<>(ResultCode.UNAUTHORIZED);
        out.println(result);
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

    private void initAuthOrIgnoreArr() {
        if (authUrlArr == null){
            authUrlArr = authUrlString.split(separator);
        }
        if (ignoreUrlArr == null){
            ignoreUrlArr = ignoreUrlString.split(separator);
        }
        if (ignoreSuffixArr == null){
            ignoreSuffixArr = ignoreSuffixString.split(separator);
        }
    }

    private boolean mathAuthUrl(String servletPath) {
        //如果url不需要验证，则不拦截
        for (String temp : authUrlArr){
            if (StringUtils.isEmpty(temp) || !servletPath.startsWith(temp)){
                return true;
            }
        }

        //如果url不需要过滤，则不拦截
        for (String temp : ignoreUrlArr){
            if (StringUtils.isEmpty(temp) || servletPath.startsWith(temp)){
                return true;
            }
        }

        //部分后缀不拦截
        for (String temp : ignoreSuffixArr){
            if (StringUtils.isEmpty(temp) || servletPath.endsWith(temp)){
                return true;
            }
        }
        return false;
    }
}
