package com.jtj.web.common.shiro;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.aop.AuthorizingAnnotationHandler;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/8/7.
 */
public class MyPermissionAnnotationHandler extends AuthorizingAnnotationHandler {

    public MyPermissionAnnotationHandler() {
        super(RequiresPermissions.class);
    }

    protected String[] getAnnotationValue(Annotation a) {
        RequiresPermissions rpAnnotation = (RequiresPermissions)a;
        return rpAnnotation.value();
    }

    public void assertAuthorized(Annotation a) throws AuthorizationException {
        if (a instanceof RequiresPermissions) {
            RequiresPermissions rpAnnotation = (RequiresPermissions)a;
            String[] perms = this.getAnnotationValue(a);
            Subject subject = this.getSubject();

            //todo 无状态请求 判断权限
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String securityKey = request.getParameter("securityKey");
            if (!StringUtils.isEmpty(securityKey)){
                throw new AuthorizationException("现在暂未开通无状态权限");
            }

            if (perms.length == 1) {
                subject.checkPermission(perms[0]);
            } else if (Logical.AND.equals(rpAnnotation.logical())) {
                this.getSubject().checkPermissions(perms);
            } else {
                if (Logical.OR.equals(rpAnnotation.logical())) {
                    boolean hasAtLeastOnePermission = false;
                    String[] var6 = perms;
                    int var7 = perms.length;

                    for(int var8 = 0; var8 < var7; ++var8) {
                        String permission = var6[var8];
                        if (this.getSubject().isPermitted(permission)) {
                            hasAtLeastOnePermission = true;
                        }
                    }

                    if (!hasAtLeastOnePermission) {
                        this.getSubject().checkPermission(perms[0]);
                    }
                }

            }
        }
    }
}
