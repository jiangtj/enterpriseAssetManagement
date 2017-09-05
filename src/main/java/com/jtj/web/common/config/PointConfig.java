package com.jtj.web.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

/**
 * Created by jiang (jiang.taojie@foxmail.com)
 * 2017/8/21 21:59 End.
 */
@Deprecated
//@Component
@ConfigurationProperties("point.client")
public class PointConfig {

    private boolean enabled;
    private String name;
    private String url;
    private String path;
    private String username;
    private String password;

    public boolean hasBasicAuth(){
        return !StringUtils.isEmpty(username);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
