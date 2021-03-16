package com.softbd.onlinequiz.service;

import com.softbd.onlinequiz.entity.Result;

public interface ResultService {

	public Result insert(Result e);
	
	public Result selectByUserId(int id);
}
