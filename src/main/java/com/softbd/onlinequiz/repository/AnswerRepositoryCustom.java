package com.softbd.onlinequiz.repository;

import com.softbd.onlinequiz.entity.Answer;

public interface AnswerRepositoryCustom {

	public Answer selectByIdAndAnswer(int id, String answer);
}
