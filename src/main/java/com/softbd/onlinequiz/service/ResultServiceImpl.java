package com.softbd.onlinequiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softbd.onlinequiz.entity.Result;
import com.softbd.onlinequiz.repository.ResultRepository;

@Service
public class ResultServiceImpl implements ResultService{
	
	@Autowired
	ResultRepository resultRepository;

	@Override
	public Result insert(Result r) {
		return resultRepository.save(r);
	}

	@Override
	public Result selectByUserId(int id) {
		return resultRepository.selectByUserId(id);
	}

	
	
	
}
