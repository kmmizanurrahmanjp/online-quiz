package com.softbd.onlinequiz.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.softbd.onlinequiz.entity.User;
import com.softbd.onlinequiz.service.ResultService;
import com.softbd.onlinequiz.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ResultService resultService;

	
	@RequestMapping(value="/active", method=RequestMethod.GET)
	public ResponseEntity<?> getActiveUser(){
		Map<String, String> res = new HashMap<String, String>();
		User u = userService.selectActiveUser();
		res.put("isActive", "N");
		if(!ObjectUtils.isEmpty(u)) {
			res.put("isActive", "Y");
			res.put("id", String.valueOf(u.getUserId()));
			res.put("name", u.getName());
		}
		return new ResponseEntity<>(res, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@PostMapping(path = "/login")
	public ResponseEntity<?> login(@RequestParam(value = "id", required = true) int id
			, @RequestParam(value = "password", required = true) String password){
		Map<String, String> res = new HashMap<String, String>();
		User u = userService.login(id, password);
		res.put("login", "N");
		if(!ObjectUtils.isEmpty(u)) {
			u.setLoginFlag(1);
			userService.insert(u);
			res.put("login", "Y");
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
    }
	
	
	@GetMapping(path = "/logout")
	public ResponseEntity<?> logout(){
		User u = userService.selectActiveUser();
		u.setLoginFlag(0);
		userService.insert(u);
		return new ResponseEntity<>("ok", HttpStatus.OK);
    }
	
	
}
