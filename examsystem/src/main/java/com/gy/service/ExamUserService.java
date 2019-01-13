package com.gy.service;

import com.gy.model.ExamUser;

public interface ExamUserService {

	ExamUser findByNameAndPwd(String username, String p);

}
