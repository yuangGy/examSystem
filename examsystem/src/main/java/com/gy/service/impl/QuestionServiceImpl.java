package com.gy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gy.dao.QuestionMapper;
import com.gy.model.Question;
import com.gy.service.QuestionService;
@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionMapper questionMapper;
	@Override
	public int addQuestion(Question question) {
		return questionMapper.insertSelective(question);
	}
	@Override
	public int deleteQuestion(int questionId) {
		return questionMapper.deleteByPrimaryKey(questionId);
	}
	@Override
	public void updateQuestion(Question question) {
		questionMapper.updateByPrimaryKeySelective(question);
	}
	@Override
	public List<Question> selectAllQuestionRamd() {
		return questionMapper.selectAllQuestionRamd();
	}
	@Override
	public List<Question> selectAllQuestionRamdmany() {
		// TODO Auto-generated method stub
		return questionMapper.selectAllQuestionRamdmany();
	}

}
