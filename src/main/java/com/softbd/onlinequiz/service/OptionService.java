package com.softbd.onlinequiz.service;

import java.util.List;

import com.softbd.onlinequiz.entity.Option;

public interface OptionService {

	public List<Option> selectAll();
	public Option selectById(int id);
	public Option selectByQuestionNo(int questionNo);
}
