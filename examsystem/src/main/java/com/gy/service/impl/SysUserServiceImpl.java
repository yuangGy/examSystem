package com.gy.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gy.dao.SysUserMapper;
import com.gy.model.MyToken;
import com.gy.model.SysUser;
import com.gy.service.SysUserService;
@Service
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;
	@Override
	public SysUser findByNameAndPwd(String username, String p) {
		return sysUserMapper.findByNameAndPwd(username,p);
	}
	@Override
	public String login(SysUser sysUser,String string, Model model) {
		Subject cSubject = SecurityUtils.getSubject();
		if(!cSubject.isAuthenticated()) {
			MyToken token = new MyToken(sysUser.getSysUserName(),sysUser.getSysUserPassword(),1);
			token.setRememberMe(true);
			try {
				cSubject.login(token);
			} catch (AuthenticationException e) {
				model.addAttribute("messge","用户名或密码错误，请重新输入");
				return "sys/syslogin";
			}
		}
		return "sys/sysmain";
	}

}
