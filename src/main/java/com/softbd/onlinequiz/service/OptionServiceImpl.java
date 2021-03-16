package com.softbd.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softbd.onlinequiz.entity.Option;
import com.softbd.onlinequiz.repository.OptionRepository;

@Service
public class OptionServiceImpl implements OptionService{
	
	@Autowired
	OptionRepository repo;

	@Override
	public List<Option> selectAll() {
		return repo.findAll();
	}

	@Override
	public Option selectById(int id) {
		Optional<Option> question = repo.findById(id);
		return question.isPresent() ? question.get() : null;
		
	}

	@Override
	public Option selectByQuestionNo(int questionNo) {
		return null;
	}
	
	
	
}
