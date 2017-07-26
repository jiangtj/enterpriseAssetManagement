package com.jtj.web.common.config;

import com.jtj.web.common.exception.AuthenticationAssetException;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.utils.MD5String;
import com.jtj.web.dao.UserDao;
import com.jtj.web.dto.UsernamePasswordTokenDto;
import com.jtj.web.entity.User;
import com.jtj.web.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/7/26.
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordTokenDto token = (UsernamePasswordTokenDto) authenticationToken;
        User user = userDao.getUserByName(token.getUsername());
        ResultDto<Object> result = new ResultDto<>();

        if (user == null) {
            result.setResultCode(ResultCode.USER_INFO_NON_EXISTENT);
            result.setMessage("请重新检测帐号是否输错！");
            throw new AuthenticationAssetException(result);
        }

        Long time = token.getTime();
        Long serverTime = new Date().getTime();
        Long timeInterval = serverTime -time;
        if (timeInterval > 10*60*1000 || timeInterval < -10*60*1000){
            result.setResultCode(ResultCode.USER_TIME_ERROR);
            result.setMessage("请校准时间后再次登录!");
            throw new AuthenticationAssetException(result);
        }

        String MD5Password = MD5String.getMD5Str(user.getPassword() + time);
        if (!MD5Password.equals(new String(token.getPassword()))){
            result.setResultCode(ResultCode.USER_PASSWORD_ERROR);
            throw new AuthenticationAssetException(result);
        }

        return new SimpleAuthenticationInfo(user,MD5Password,getName());
    }
}
