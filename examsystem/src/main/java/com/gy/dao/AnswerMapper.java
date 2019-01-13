package com.gy.dao;

import org.apache.ibatis.annotations.Param;

import com.gy.model.Answer;

public interface AnswerMapper {
    int deleteByPrimaryKey(Integer answerId);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(Integer answerId);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);

	void deleteAllAnswerByQuestionId(@Param("questionId")int questionId);
}