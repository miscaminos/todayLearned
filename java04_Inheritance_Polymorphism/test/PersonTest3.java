package com.encore.test;

import com.encore.child.Manager;
import com.encore.child.Student;
import com.encore.parent.Person;
import com.encore.service.PersonService;
import com.encore.util.MyDate;

public class PersonTest3 {

	public static void main(String[] args) {
	
		Person s1 = new Student("Student1",new MyDate(1990,10,4),"NY","Data Analysis");
		Person s2= new Student("Student2",new MyDate(1989,10,4),"Pennsylvania","Python");
		Person s3= new Student("Student3",new MyDate(1995,10,4),"Texas","AI");
		Person m1 = new Manager("Manager1",new MyDate(1980,3,2),"Alabama","Marketing",100,200);
		Person m2 = new Manager("Manager2",new MyDate(1980,3,2),"Delaware","IT",100,200);
		Person m3 = new Manager("Manager3",new MyDate(1980,3,2),"Texas","HR",100,200);
		
		Person[] people = {s1,s2,s3,m1,m2,m3};
		
		//PersonService ps = new PersonService();<--PersonService가 SingleTone이기때문에  ps 생성 불가. 
		
		//1~4번 까지의 메소드를 정의... 이곳에서 호출해서 완성...
		//1.
		System.out.println("===1. 모든정보를 출력===");
		PersonService.getInstance().getAllPerson(people);
		//2.
		System.out.println("===2. 이름으로 사람을 찾기===");
		PersonService.getInstance().findPersonByName(people, "Student3");
		//3. 
		System.out.println("===3. 특정 사람의 연봉===");
		System.out.println(m2.getName()+"의 연봉: "+PersonService.getInstance().getAnnualSalary(people,m2));
		//4.
		System.out.println("===4. 연간 Total Cost===");
		System.out.println("연 cost는:"+PersonService.getInstance().getTotalCost(people));
		
	}

}
