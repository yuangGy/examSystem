package com.gy.aop;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gy.model.SysUser;
import com.gy.model.SysUserLog;
import com.gy.service.SysUserLogService;

@Component
@Aspect
public class SysUserLogAop {
	
	@Autowired
	private SysUserLogService sysUserLogService;
	
	private Executor executor = Executors.newSingleThreadExecutor();
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
	
	@AfterReturning(value="execution(* com.gy.service.SysUserService.login(..))",returning="result")
	public void doLog(JoinPoint jp,String result) {
		Object[] args = jp.getArgs();
		SysUser sysUser = (SysUser) args[0];
		SysUserLog sysUserLog = new SysUserLog();
		sysUserLog.setSysUserLogId(UUID.randomUUID().toString());
		sysUserLog.setSysUserName(sysUser.getSysUserName());
		Date date = new Date();
		String time = sdf.format(date);
		sysUserLog.setSysUserLogDate(time);
		sysUserLog.setSysUserLogAddress((String)args[1]);
		if("sys/sysmain".equals(result)) {
			executor.execute(()->{
				int flag = sysUserLogService.addSysUserLog(sysUserLog);
			});
			
		}
	}
}
