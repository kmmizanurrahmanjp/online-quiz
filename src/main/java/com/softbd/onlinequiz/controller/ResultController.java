package com.softbd.onlinequiz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softbd.onlinequiz.entity.Result;
import com.softbd.onlinequiz.entity.User;
import com.softbd.onlinequiz.service.ResultService;
import com.softbd.onlinequiz.service.UserService;

@RestController
@RequestMapping("api/result")
public class ResultController {
	
	@Autowired
	ResultService resultService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<?> selectResult(){
		User u = userService.selectActiveUser();
		Result r = resultService.selectByUserId(u.getUserId());
		return new ResponseEntity<>(r, new HttpHeaders(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/refresh", method=RequestMethod.GET)
	public ResponseEntity<?> refrashResult(){
		User u = userService.selectActiveUser();
		Result r = resultService.selectByUserId(u.getUserId());
		r.setTotalMark(0);
		resultService.insert(r);
		return new ResponseEntity<>("Ok", new HttpHeaders(), HttpStatus.OK);
	}
	
}
