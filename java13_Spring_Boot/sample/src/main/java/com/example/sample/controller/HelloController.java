package com.example.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
	@GetMapping
	@ResponseBody
	public String Hello() {
		return "Hello World";
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
}
