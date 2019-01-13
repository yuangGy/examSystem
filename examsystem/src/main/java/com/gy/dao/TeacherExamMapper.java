package com.gy.dao;

import com.gy.model.TeacherExam;

public interface TeacherExamMapper {
    int deleteByPrimaryKey(Integer techerExamId);

    int insert(TeacherExam record);

    int insertSelective(TeacherExam record);

    TeacherExam selectByPrimaryKey(Integer techerExamId);

    int updateByPrimaryKeySelective(TeacherExam record);

    int updateByPrimaryKey(TeacherExam record);

	int selectMaxType();
}