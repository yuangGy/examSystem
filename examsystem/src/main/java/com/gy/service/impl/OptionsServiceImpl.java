package com.gy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gy.dao.OptionsMapper;
import com.gy.model.Options;
import com.gy.service.OptionsService;
@Service
public class OptionsServiceImpl implements OptionsService {
	
	@Autowired
	private OptionsMapper optionsMapper;
	@Override
	public void addOptions(Options options) {
		optionsMapper.insertSelective(options);
	}
	@Override
	public void deleteAllOptionsByQuestionId(int questionId) {
		optionsMapper.deleteAllOptionsByQuestionId(questionId);
	}
	

}
