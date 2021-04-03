package com.example.validator;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalcController3 {
	public CalcController3() {
		System.out.println("----------->CalcController3 created.");
	}

	// http://localhost:8000/calc3
	@GetMapping("/calc3")
	public String calc() {
		return "/calc/form3";

	}

	// 에러가 발생했을때 자동으로 이전 폼으로 이동
	@PostMapping("/calc3")
	public String calc(@Valid CalcVOAnnotation calcVO, BindingResult result, Model model) {

		Map<String, String> errors = new HashMap<String, String>();

		if (result.hasErrors()) { // 에러 발생시
			if (result.getFieldError("menu") != null) {
				System.out.println("menu: " + result.getFieldError("menu").getDefaultMessage());
				errors.put("menu", "menu가 등록이 누락되었습니다.");
			}

			if (result.getFieldError("price") != null) {
				System.out.println("price: " + result.getFieldError("price").getDefaultMessage());
				errors.put("price", "금액은 1000원이상 천만원 이하여야합니다");

			}

			if (result.getFieldError("count") != null) {
				System.out.println("count: " + result.getFieldError("count").getDefaultMessage());
				errors.put("count", "수량은 1개이상 천개 이하여야합니다.");

			}

			model.addAllAttributes(errors);
			return "/calc/form3";
		} else { // 에러 미발생
			int payment = calcVO.getPrice() * calcVO.getCount();
			model.addAttribute("payment", payment);
			return "/calc/proc";
		}
	}
}
