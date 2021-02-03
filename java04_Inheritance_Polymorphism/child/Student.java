package com.encore.child;

import com.encore.util.MyDate;
import com.encore.parent.Person;

public class Student extends Person{
	private String course;

	public Student(String name, MyDate birthDate, String address, String course) {
		super(name, birthDate, address);
		this.course = course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getCourse() {
		return course;
	}

	@Override
	public String toString() {
		return super.toString()+"," + course;//super keyword를 넣는게 가독성을 높힘.
	}
	

}
