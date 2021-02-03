package com.encore.parent;

import com.encore.util.MyDate;

//Super Class...
//vo class 작성
/*
 * vo class 작성 시, 항상 하는 단계:
 * 필드 선언
 * 생성자
 * setter()/getter()
 * toString() overriding
 */

public class Person {
	private String name;
	private MyDate birthDate;
	private String address;
	
	public Person(String name, MyDate birthDate, String address) {
		super();//필드 초기화 하기 직전에 먼저 부모를 생성해야한다.
		this.name = name;
		this.birthDate = birthDate;
		this.address = address;
	}

	@Override
	public String toString() {
		//return "Person [name=" + name + ", birthDate=" + birthDate + ", address=" + address + "]";
		return name+","+birthDate+","+address;
		//return name+","+birthDate.toString()+","+address;// 위와 똑같음. Java에서 define된 .toString()의 역할이 그렇기 때문이다.
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirthDate(MyDate birthDate) {
		this.birthDate = birthDate;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	


	public String getName() {
		return name;
	}

	public MyDate getBirthDate() {
		return birthDate;
	}

	public String getAddress() {
		return address;
	}

	
}
