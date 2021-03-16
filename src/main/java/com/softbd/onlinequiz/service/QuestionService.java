package com.softbd.onlinequiz.service;

import java.util.List;

import com.softbd.onlinequiz.entity.Question;

public interface QuestionService {

	public List<Question> selectAll();
	public Question selectById(int id);
}
