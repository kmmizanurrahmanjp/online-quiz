package com.softbd.onlinequiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.softbd.onlinequiz.service.QuestionService;

@RestController
@RequestMapping("api/question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;

	
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> selectQuestion(){
		return new ResponseEntity<>(questionService.selectAll(), new HttpHeaders(), HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<?> selectQuestionById(@PathVariable int id){
		return new ResponseEntity<>(questionService.selectById(id), new HttpHeaders(), HttpStatus.OK);
	}
	
	
}
