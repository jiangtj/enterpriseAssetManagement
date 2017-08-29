package com.jtj.web.common.config;

import com.jtj.web.common.shiro.MyAuthorizationAttributeSourceAdvisor;
import com.jtj.web.common.shiro.MyFormAuthenticationFilter;
import com.jtj.web.common.shiro.MyPermissionsAuthorizationFilter;
import com.jtj.web.common.shiro.ShiroRealm;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/7/26.
 */
@Configuration
public class ShiroConfig {

    @Bean
    public SimpleCookie rememberMeCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey(Base64.decode("bWluZS1hc3NldC1rZXk6QQ=="));
        return cookieRememberMeManager;
    }

    @Bean
    public Realm shiroRealm() {
        return new ShiroRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager(Realm realm) {
        DefaultWebSecurityManager sm = new DefaultWebSecurityManager();
        sm.setRealm(realm);
        sm.setRememberMeManager(rememberMeManager());
        return sm;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager sm) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setLoginUrl("/login");
        shiroFilter.setSuccessUrl("/index");
        shiroFilter.setUnauthorizedUrl("/forbidden");
        Map<String, String> filterChainDefinitionMapping = new LinkedHashMap<>();

        filterChainDefinitionMapping.put("/", "anon");
        filterChainDefinitionMapping.put("/error", "anon");
        filterChainDefinitionMapping.put("/init", "anon");
        filterChainDefinitionMapping.put("/about", "anon");
        filterChainDefinitionMapping.put("/public/**", "anon");
        filterChainDefinitionMapping.put("/css/**", "anon");
        filterChainDefinitionMapping.put("/example/**", "anon");
        filterChainDefinitionMapping.put("/font-awesome/**", "anon");
        filterChainDefinitionMapping.put("/fonts/**", "anon");
        filterChainDefinitionMapping.put("/img/**", "anon");
        filterChainDefinitionMapping.put("/js/**", "anon");
        filterChainDefinitionMapping.put("/static/**", "anon");

        filterChainDefinitionMapping.put("/**", "authc");

        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
        shiroFilter.setSecurityManager(sm);

        Map<String, Filter> filters = new HashMap<>();
        filters.put("authc", new MyFormAuthenticationFilter());
        filters.put("perms", new MyPermissionsAuthorizationFilter());

        shiroFilter.setFilters(filters);
        return shiroFilter;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new MyAuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
