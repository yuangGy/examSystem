package com.gy.service;

import com.gy.model.Options;

public interface OptionsService {

	void addOptions(Options options);

	void deleteAllOptionsByQuestionId(int parseInt);

}
