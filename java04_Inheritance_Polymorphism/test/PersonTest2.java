package com.encore.test;

import com.encore.child.Manager;
import com.encore.child.Student;
import com.encore.parent.Person;
import com.encore.util.MyDate;

public class PersonTest2 {

	public static void main(String[] args) {
		//상속X
		Student s11 = new Student("Student1",new MyDate(1990,10,4),"NY","Data Analysis");
		Student s22= new Student("Student2",new MyDate(1989,10,4),"NY","Python");
		Student s33= new Student("Student3",new MyDate(1995,10,4),"Texas","AI");
		Manager m11 = new Manager("Manage1",new MyDate(1980,3,2),"Texas","Marketing",1,2);
		
		Student[] sarr = {s11,s22,s33};//same Data Type만 - Homogeneous Collection
		// Encore Academy 입장에서는 관리하기 힘든 코드가 나올 수 밖에 없다.
		
		///////////////////////////////////////////////////////////////////////////////////
		//상속O::Polymorphism을 사용한다.
		Person s1 = new Student("Student1",new MyDate(1990,10,4),"NY","Data Analysis");
		Person s2= new Student("Student2",new MyDate(1989,10,4),"NY","Python");
		Person s3= new Student("Student3",new MyDate(1995,10,4),"Texas","AI");
		Person m1 = new Manager("Manage1",new MyDate(1980,3,2),"Texas","Marketing",1,2);
		
		Person[] parr = {s1,s2,s3,m1}; //diff Data Type도 넣을 수 있다-Heterogeneous Collection
		// Encore Academy 입장에서 관리하기 효율적인 코드가 나올 수 있다.
		
		for(Person p: parr) System.out.println(p);
		
	}

}
