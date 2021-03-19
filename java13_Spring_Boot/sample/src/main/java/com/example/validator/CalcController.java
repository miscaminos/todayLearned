package com.example.validator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CalcController {
	public CalcController() {
		System.out.println("-----> CalcController created");
	}
	
	@GetMapping("/calc")
	public String calc() {
		return "/calc/form"; 
	}
	
	@PostMapping("/calc")
	public String calc(CalcVO vo, BindingResult result, Model model) {
		CalcValidator validator = new CalcValidator();
		validator.validate(vo, result); //검증 (서버단에서의 검증)
		
		if(result.hasErrors()) {
			return "/calc/form"; //오류로 다시 form으로 되돌아 감.
		}else {
			//에러 미 발생이면 정상 처리될 페이지로 데이터를 보낸다
			int payment = vo.getPrice() * vo.getCount();
			model.addAttribute("payment", payment);
			
			return "/calc/proc";
		}
	}
}
