package com.gy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gy.dao.SysUserLogMapper;
import com.gy.model.SysUserLog;
import com.gy.service.SysUserLogService;

@Service
public class SysUserLogServiceImpl implements SysUserLogService {
	
	@Autowired
	private SysUserLogMapper sysUserLogMapper;

	@Override
	public int addSysUserLog(SysUserLog sysUserLog) {
		return sysUserLogMapper.insertSelective(sysUserLog);
	}
}