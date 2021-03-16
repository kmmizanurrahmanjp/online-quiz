package com.softbd.onlinequiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softbd.onlinequiz.entity.Option;
import com.softbd.onlinequiz.entity.Question;
import com.softbd.onlinequiz.repository.OptionRepository;
import com.softbd.onlinequiz.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	QuestionRepository repo;
	@Autowired
	OptionRepository optionRepo;

	@Override
	public List<Question> selectAll() {
		
		List<Question> res = new ArrayList<>();
		
		List<Question> questions = repo.findAll();
		for(Question q : questions) {
			Option option = optionRepo.selectByQuestionNo(q.getQuestionNo());
			q.setOptions(option);
			res.add(q);
		}
		return res;
	}

	@Override
	public Question selectById(int id) {
		//Optional<Question> question = repo.findById(id);
		Question q = repo.findById(id).get();
		Option option = optionRepo.selectByQuestionNo(q.getQuestionNo());
		q.setOptions(option);
		return q;
		
	}
	
	
	
}
