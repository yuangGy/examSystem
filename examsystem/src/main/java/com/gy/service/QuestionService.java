package com.gy.service;

import java.util.List;

import com.gy.model.Question;

public interface QuestionService {

	int addQuestion(Question question);

	int deleteQuestion(int parseInt);

	void updateQuestion(Question question);

	List<Question> selectAllQuestionRamd();

	List<Question> selectAllQuestionRamdmany();

}
