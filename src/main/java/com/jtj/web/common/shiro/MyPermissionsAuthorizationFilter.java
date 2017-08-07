package com.jtj.web.common.shiro;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/8/7.
 */
public class MyPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        return super.isAccessAllowed(request, response, mappedValue);
    }
}
