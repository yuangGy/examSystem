package com.gy.dao;

import org.apache.ibatis.annotations.Param;

import com.gy.model.ExamUser;

public interface ExamUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(ExamUser record);

    int insertSelective(ExamUser record);

    ExamUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(ExamUser record);

    int updateByPrimaryKey(ExamUser record);

	ExamUser findByNameAndPwd(@Param("loginName")String username, @Param("password")String p);
}