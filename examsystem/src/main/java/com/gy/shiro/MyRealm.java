package com.gy.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gy.model.MyToken;
import com.gy.service.ExamUserService;
import com.gy.service.SysUserService;

public class MyRealm extends AuthorizingRealm {

	@Autowired
	private ExamUserService examUserService;

	@Autowired
	private SysUserService sysUserService;

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		MyToken t = (MyToken) token;
		String username = t.getUsername();
		String password = new String(t.getPassword());
		int type = t.getType();
		// 加密后的密文
		String p = new SimpleHash("Md5", password, username, 1024).toString();
		AuthenticationInfo info = null;
		if (examUserService.findByNameAndPwd(username, p) != null && type == 0) {// 登录成功

			info = new SimpleAuthenticationInfo(username, password, getName());
		} else if (sysUserService.findByNameAndPwd(username, p) != null && type == 1) {// 登录成功

			info = new SimpleAuthenticationInfo(username, password, getName());
		} else {
			throw new AuthenticationException();
		}
		return info;
	}

	@Test
	public void name() {
		System.out.println(new SimpleHash("Md5", "123", "admin", 1024).toString());
	}

}
