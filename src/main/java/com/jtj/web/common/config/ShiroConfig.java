package com.jtj.web.common.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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
    public Realm shiroRealm() {
        return new ShiroRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager(Realm realm) {
        DefaultWebSecurityManager sm = new DefaultWebSecurityManager();
        sm.setRealm(realm);
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
        filterChainDefinitionMapping.put("/welcome", "anon");
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
        filters.put("authc", new FormAuthenticationFilter());

        shiroFilter.setFilters(filters);
        return shiroFilter;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
