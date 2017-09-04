package com.jtj.web.dto;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/7/26.
 */
public class UsernamePasswordTokenDto extends UsernamePasswordToken {

    private Long loginTime;

    public UsernamePasswordTokenDto(){

    }

    public UsernamePasswordTokenDto(String username, String password, Long loginTime) {
        super(username, password);
        this.loginTime = loginTime;
    }

    public UsernamePasswordTokenDto(String username, String password, boolean rememberMe, Long loginTime) {
        super(username, password, rememberMe);
        this.loginTime = loginTime;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }
}
