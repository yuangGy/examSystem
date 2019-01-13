package com.gy.service;

import com.gy.model.Answer;

public interface AnswerService {

	void addAnswer(Answer answer);

	void deleteAllAnswerByQuestionId(int parseInt);

}
