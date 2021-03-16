package com.softbd.onlinequiz.repository;

import com.softbd.onlinequiz.entity.Result;

public interface ResultRepositoryCustom {

	public Result selectByUserId(int userId);
}
