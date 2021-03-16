package com.softbd.onlinequiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.softbd.onlinequiz.entity.Answer;
import com.softbd.onlinequiz.entity.Result;
import com.softbd.onlinequiz.entity.User;
import com.softbd.onlinequiz.repository.AnswerRepository;
import com.softbd.onlinequiz.repository.ResultRepository;
import com.softbd.onlinequiz.repository.UserRepository;

@Service
public class AnswerServiceImpl implements AnswerService{
	
	@Autowired
	AnswerRepository repo;
	@Autowired
	ResultRepository resultRepo;
	@Autowired
	UserRepository userRepo;
	@Autowired
	QuestionService questionService;

	@Override
	public List<Answer> selectAll() {
		return repo.findAll();
	}

	@Override
	public Answer selectById(int id) {
		Optional<Answer> answer = repo.findById(id);
		return answer.isPresent() ? answer.get() : null;
		
	}

	@Override
	public void saveResult(int questionNo, String answer) {
		Answer a = repo.selectByIdAndAnswer(questionNo, answer);
		User u = userRepo.selectActiveUser();
		Result r = resultRepo.selectByUserId(u.getUserId());
		int previousMark = r.getTotalMark();
		int questionAddMark = questionService.selectById(questionNo).getMark();
		int questionSubMark = 2;
		
		if(ObjectUtils.isEmpty(a)) {
			r.setTotalMark(previousMark - questionSubMark);
			resultRepo.save(r);
			
		}else {
			r.setTotalMark(previousMark + questionAddMark);
			resultRepo.save(r);
		}
		
	}
	
}
