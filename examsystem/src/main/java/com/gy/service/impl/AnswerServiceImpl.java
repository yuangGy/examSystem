package com.gy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gy.dao.AnswerMapper;
import com.gy.model.Answer;
import com.gy.service.AnswerService;
@Service
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	private AnswerMapper answerMapper;
	@Override
	public void addAnswer(Answer answer) {
		answerMapper.insertSelective(answer);
	}
	@Override
	public void deleteAllAnswerByQuestionId(int parseInt) {
		answerMapper.deleteAllAnswerByQuestionId(parseInt);
	}

}
