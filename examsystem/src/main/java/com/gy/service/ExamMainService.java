package com.gy.service;

import java.util.List;

import com.gy.model.Subject;

public interface ExamMainService {

	List<Subject> findAllSimpleSucjects();

	List<Subject> findAllManySucjects();

	List<Subject> sysFindAllSimpleSucjects();

	List<Subject> sysFindAllManySucjects();

	String addNewQuestion(String type, String questionScore, String questionName, String[] sysoption,
			String[] optionsDesc);

	Subject sysFindSimpleSucject(int parseInt);

	String updateQuestion(String type, String questionId, String questionScore, String questionName, String[] sysoption,
			String[] optionsDesc);

	void deleteQuestion(String questionId);

	boolean saveQuestions(String simpleScore, String manyScore);
}
