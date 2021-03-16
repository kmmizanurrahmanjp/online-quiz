package com.softbd.onlinequiz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class UIController {

	@GetMapping
	public String goIndexPage() {
		return "index";
	}
}
