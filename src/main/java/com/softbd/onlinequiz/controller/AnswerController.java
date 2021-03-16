package com.softbd.onlinequiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softbd.onlinequiz.service.AnswerService;

@RestController
@RequestMapping("api/answer")
public class AnswerController {
	
	@Autowired
	AnswerService answerService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> selectAnswer(){
		 return new ResponseEntity<>(answerService.selectAll(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<?> selectAnswerById(@PathVariable int id){
		return new ResponseEntity<>(answerService.selectById(id), new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@PostMapping
	public ResponseEntity<?> addAnswer(@RequestParam(value = "questionNo", required = true) int questionNo
			, @RequestParam(value = "answer", required = false) String answer){
		System.out.println(answer);
		if(!StringUtils.isEmpty(answer)) {
			answerService.saveResult(questionNo, answer);
		}
		
		return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
	
	
}
