package com.gy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gy.dao.ExamUserMapper;
import com.gy.model.ExamUser;
import com.gy.service.ExamUserService;

@Service
public class ExamUserServiceImol implements ExamUserService {

	@Autowired
	private ExamUserMapper examUserMapper;
	
	@Override
	public ExamUser findByNameAndPwd(String username, String p) {
		return examUserMapper.findByNameAndPwd(username,p);
	}

}
