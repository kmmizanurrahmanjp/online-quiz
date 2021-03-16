package com.softbd.onlinequiz.service;

import java.util.List;

import com.softbd.onlinequiz.entity.Answer;

public interface AnswerService {

	public List<Answer> selectAll();
	public Answer selectById(int id);
	public void saveResult(int questionNo, String answer);
}
