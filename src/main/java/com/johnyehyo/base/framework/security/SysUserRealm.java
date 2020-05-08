package com.johnyehyo.base.framework.security;

import com.johnyehyo.base.common.constant.Constants;
import com.johnyehyo.base.system.domain.AdminEntity;
import com.johnyehyo.base.system.service.ISysAdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * @author JohnYehyo
 * @date 2020-4-16
 */
public class SysUserRealm extends AuthorizingRealm {

    @Resource
    private ISysAdminService sysAdminService;

    private static final Logger logger = LoggerFactory.getLogger(SysUserRealm.class);
    public static final String KEY_USER = "user";

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

//        LoginedUser loginedUser = (LoginedUser) principals.getPrimaryPrincipal();
//        String username = loginedUser.getShortName();

        String username = (String) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        authorizationInfo.setRoles(sysAdminService.findRoles(username));
//        authorizationInfo.setStringPermissions(sysAdminService.findPermissions(username));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String account = (String) token.getPrincipal();

        Subject subject;
        AdminEntity admin = sysAdminService.getAdmin(account);
        if (admin == null) {
            logger.error("账号错误:"+account);
            throw new UnknownAccountException();
        } else {
            subject = SecurityUtils.getSubject();
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                admin.getT_account(),
                admin.getT_password(),
                getName()
        );

        Session session = subject.getSession();
        //暂时简单存储到HttpSession
        session.setAttribute(Constants.LOGIN_USER, admin);
        MDC.put(KEY_USER,admin.getT_username());

        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }
}
