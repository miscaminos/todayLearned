package com.example.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//controller 에서 form의 데이터 유효성 검증
public class CalcValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		//검증할 객체의 클래스 타입 정보 명시
		//<?>는 generic type. 어떤 클래스를 검증할 것인지 사용 시 지정.
		return CalcVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		CalcVO calcVO = (CalcVO)target;
		//form submit시, Object 타입으로 from에서 정보를 받아옴.
		//그 중 menu, price, count사용
		
		String menu = calcVO.getMenu();
		if(menu == null || menu.trim().isEmpty()) {
			System.out.println("menu 등록이 누락 되었습니다.");
			errors.rejectValue("menu", "error"); //임의값 지정, 에러발생 나타냄
		}
		
		int price = calcVO.getPrice();
		if(price<1000 || price >=1000000) {
			System.out.println("금액은 1000원 이상 천만원 이하여야합니다");
			errors.reject("price","error");
		}
		
		int count = calcVO.getCount();
		if(count <=0 || count >=1000) {
			System.out.println("수량은 1개이상 100개 이하여야합니다");
			errors.reject("count","error");
		}
		

	}

}
