package com.gy.dao;

import org.apache.ibatis.annotations.Param;

import com.gy.model.Options;

public interface OptionsMapper {
    int deleteByPrimaryKey(Integer optionsId);

    int insert(Options record);

    int insertSelective(Options record);

    Options selectByPrimaryKey(Integer optionsId);

    int updateByPrimaryKeySelective(Options record);

    int updateByPrimaryKey(Options record);

	void deleteAllOptionsByQuestionId(@Param("questionId")int questionId);
}