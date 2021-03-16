package com.softbd.onlinequiz.repository;

import com.softbd.onlinequiz.entity.Option;

public interface OptionRepositoryCustom {

	public Option selectByQuestionNo(int questionNo);
}
