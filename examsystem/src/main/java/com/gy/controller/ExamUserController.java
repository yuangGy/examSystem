package com.gy.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gy.model.ExamUser;
import com.gy.model.MyToken;

@Controller
@RequestMapping("/examUserController")
public class ExamUserController {
	
	
	@RequestMapping("/examlogin")
	@ResponseBody
	public String examlogin(ExamUser examUser,HttpServletRequest request,HttpServletResponse response)  {
		Subject cSubject = SecurityUtils.getSubject();
		if(!cSubject.isAuthenticated()) {
			MyToken token = new MyToken(examUser.getLoginName(), examUser.getPassword(), 0);
			token.setRememberMe(true);
			try {
				cSubject.login(token);
			} catch (AuthenticationException e) {
				return "no";
			}
		}
		return "yes";
	}
	
	
}
