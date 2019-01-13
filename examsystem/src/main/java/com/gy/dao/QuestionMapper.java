package com.gy.dao;

import java.util.List;

import com.gy.model.Question;

public interface QuestionMapper {
    int deleteByPrimaryKey(Integer questionId);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer questionId);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

	List<Question> selectAllQuestionRamd();

	List<Question> selectAllQuestionRamdmany();
}