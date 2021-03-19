package com.example.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalcController2 {
	public CalcController2() {
		System.out.println("-----> CalcController2 created");
	}
	
	@GetMapping("/calc2")
	public String calc() {
		return "/calc/form2"; 
	}
	
	@PostMapping("/calc2")
	public String calc(CalcVO vo, BindingResult result, Model model) {
		CalcValidator validator = new CalcValidator();
		validator.validate(vo, result); //검증 (서버단에서의 검증)
		
		Map<String, String> errors = new HashMap<String, String>();
		
		if(result.hasErrors()) {
			if(result.getFieldError("menu")!=null) {
				System.out.println("menu:"+result.getFieldError().getCode());
				errors.put("menu", "menu가 등록이 누락되었습니다.");
			}
			if(result.getFieldError("price")!=null) {
				System.out.println("menu:"+result.getFieldError().getCode());
				errors.put("price", "금액은 1000원 이상 천만원 이하여야합니다.");
			}
	        if (result.getFieldError("count") != null) {
	          System.out.println("count: " + result.getFieldError("count").getCode());
	          errors.put("count","수량은 1개이상 천개 이하여야합니다.");   
	        }
	        model.addAllAttributes(errors);            
	        return "/calc/form2";//오류로 다시 form으로 되돌아 감.
		}else {
			int payment = vo.getPrice() * vo.getCount();
			model.addAttribute("payment", payment);
			return "/calc/proc";
		}
	}
}
