package com.iflytek.manager.web.shiro;

import com.iflytek.common.enumeration.user.UserStatus;
import com.iflytek.common.exception.UserNameNotFoundException;
import com.iflytek.common.model.Account;
import com.iflytek.common.model.Permission;
import com.iflytek.common.model.Role;
import com.iflytek.manager.api.AccountService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AZhao
 */
public class MyRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);
    @Autowired
    private AccountService accountService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();
        Account account = accountService.getAccount(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<String> roleList = new ArrayList<>();
        for (Role role : account.getRoleList()) {
            roleList.add(role.getRoleName());
        }
        simpleAuthorizationInfo.addRoles(roleList);
        List<String> permissionList = new ArrayList<>();
        for (Permission permission : account.getPermissionList()) {
            permissionList.add(permission.getPermissionName());
        }
        simpleAuthorizationInfo.addStringPermissions(permissionList);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     * @throws UserNameNotFoundException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException, UserNameNotFoundException {
        String userName = (String) authenticationToken.getPrincipal();
        Account account = accountService.getAccount(userName);
        if (account == null) {
            throw new IncorrectCredentialsException("账号或者密码错误");
        }
        if (account.getStatus() == UserStatus.Forbidden.getValue()) {
            throw new LockedAccountException("该账号已被禁用");
        }
        return new SimpleAuthenticationInfo(account.getMwId(), account.getPwd(), this.getName());
    }

}
