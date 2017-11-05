package com.jtj.web.common.shiro;

import com.jtj.web.common.Constant;
import com.jtj.web.common.ResultCode;
import com.jtj.web.common.ResultDto;
import com.jtj.web.common.exception.AuthenticationAssetException;
import com.jtj.web.common.utils.MD5String;
import com.jtj.web.dao.PermissionDao;
import com.jtj.web.dao.UserDao;
import com.jtj.web.dto.UsernamePasswordTokenDto;
import com.jtj.web.entity.Permission;
import com.jtj.web.entity.Point;
import com.jtj.web.entity.User;
import com.jtj.web.service.PointService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MrTT (jiang.taojie@foxmail.com)
 * 2017/7/26.
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private PointService pointService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User)principalCollection.getPrimaryPrincipal();
        Point point = pointService.getResultTreeEntityById(user.getPointId()).getObject();
        user.setPoint(point);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //系统管理员
        if (user.getId() == 1){
            info.addRole("system-administrator-role");
            info.addStringPermission("system-administrator-permission");
        }
        //角色
        info.addRole(user.getRole().getId()+"");
        //权限
        List<Permission> permissions = permissionDao.getByRoleId(user.getRoleId());
        List<String> stringPermissions = permissions.stream().map(Permission::getCode).collect(Collectors.toList());
        info.addStringPermissions(stringPermissions);
        //session
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute(Constant.SESSION_USER,user);
        session.setAttribute(Constant.SESSION_PERMISSION,info.getStringPermissions());
        session.setAttribute(Constant.SESSION_ROLE,info.getRoles());
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

        Long time = token.getLoginTime();
        Long serverTime = Instant.now().getEpochSecond();
        Long timeInterval = serverTime -time;
        if (timeInterval >= 10*60 || timeInterval <= -10*60){
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
