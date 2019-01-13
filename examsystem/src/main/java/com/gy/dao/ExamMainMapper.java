package com.gy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gy.model.Subject;

public interface ExamMainMapper {

	List<Subject> findAllSimpleSucjects();

	List<Subject> findAllManySucjects();

	List<Subject> sysFindAllSimpleSucjects();

	List<Subject> sysFindAllManySucjects();

	Subject sysFindSimpleSucject(@Param("questionId")int questionId);

	List<Subject> sysFindAllSimpleSucjectsRand();

}
