package com.gy.service;

import org.springframework.ui.Model;

import com.gy.model.SysUser;

public interface SysUserService {

	SysUser findByNameAndPwd(String username, String p);
	
	String login(SysUser sysUser, String string, Model model);
}
