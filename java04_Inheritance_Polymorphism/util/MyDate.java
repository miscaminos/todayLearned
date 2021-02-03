package com.encore.util;

public class MyDate {
	private int year;
	private int month;
	private int day;
	
	public MyDate(int year, int month, int day) {
		super();//Object를 호출하는것임.(Object는 모든 클래스의 부모다.)
		this.year = year;
		this.month = month;
		this.day = day;
	}
	//Override: 같은이름으로 추가 기능을 넣는 방법
	//상속관계의 2개 클래스(부모, 자식)에서 작용하는 기능. Override는 무조건 상속관계에서만 활용한다.
	//step1. 부모가 가진 기능을 물려받고 -- 선언부는 완전히 일치한다.
	//step2. 자식이 그 기능을 고쳐서 사용하는 것을 말한다. ---구현부는 달라진다.
	
	//Java에서는 주소값이 critical하게 사용하는 경우가 거의없다. console에 출력되는 주소값은 virtual값이다. 
	//그래서 toString()으로 주소값대신 String을 print
	//.hashcode()를 사용하면 주소값 16-bit로 print
	//"@Override"를 지우지 말것!
	@Override
	public String toString() {
		return "("+year+","+month+","+day+")";
	}
	
	//Overloading
	//상속과는 아무 관계 없이!! 하나의 클래스에서 일어나는 작용
	//overloading된 method는 argument부가 달라야한다. 처리하는 데이터가 다르다. 리턴 타입은 상관이 없음.
	public void toString(String message) {
		System.out.println(message);
	}
}
