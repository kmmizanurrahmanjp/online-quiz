package com.softbd.onlinequiz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softbd.onlinequiz.entity.Timer;
import com.softbd.onlinequiz.repository.TimerRepository;

@Service
public class TimerServiceImpl implements TimerService{
	
	@Autowired
	TimerRepository repo;

	@Override
	public Timer select() {
		return repo.findAll().get(0);
	}
}
