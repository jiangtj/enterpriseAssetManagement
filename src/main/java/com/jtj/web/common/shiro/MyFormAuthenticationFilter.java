package com.jtj.web.common.shiro;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/8/7.
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //todo 无状态请求 判断key值是否正确
        String securityKey = request.getParameter("securityKey");
        if (StringUtils.isNotEmpty(securityKey)){
            return true;
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

}
