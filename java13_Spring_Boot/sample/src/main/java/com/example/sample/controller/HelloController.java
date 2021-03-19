package com.example.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//- RestController는 기본적으로 @ResponseBody를 가지고있다.
//- Controller로 변경후 문자열 리턴시 @ResponseBody를 명시한다.

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
