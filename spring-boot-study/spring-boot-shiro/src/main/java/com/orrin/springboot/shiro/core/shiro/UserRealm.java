package com.orrin.springboot.shiro.core.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * @author Orrin on 2017/6/8.
 */
public class UserRealm extends AuthorizingRealm {

	private final static Logger logger = LoggerFactory.getLogger(UserRealm.class);

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userId = (String) principals.getPrimaryPrincipal();


		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		UsernamePasswordCaptchaToken authcToken = (UsernamePasswordCaptchaToken) token;

		String userId = (String) token.getPrincipal();

		if (authcToken == null) {
			throw new UnknownAccountException();// 没找到帐号
			//throw new LockedAccountException(); // 帐号锁定
		}

		PasswordHelper passwordHelper = new PasswordHelper();

		String salt = UUID.randomUUID().toString().toLowerCase().replaceAll("-","");
		String password = userId;
		PasswordHelper.PasswordAndSalt passwordAndSalt = passwordHelper.encryptPassword(userId,userId,salt);

		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userId, // 用户名
				passwordAndSalt.getPassword(), // 密码
				ByteSource.Util.bytes(userId+salt), // salt=username+salt
				getName() // realm name
		);

		return authenticationInfo;
	}

}
