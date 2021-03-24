package com.study.resort;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeCon {
	
	@GetMapping("/")
	public String home() {
		return "/home";
	}

}
