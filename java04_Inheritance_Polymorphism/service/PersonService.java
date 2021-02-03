package com.encore.service;

import com.encore.child.Manager;
//import com.encore.child.Student;
import com.encore.parent.Person;
/*
 * 이 클래스를 싱글톤으로 변경
 * ~Test클래스에서 객체생성 대신에 생성한 객체를 받아와서 사용하는 코드로 수정하라.
 */

public class PersonService {
	
	//PersonService class를 singleTone으로 바꾸기:
	private static PersonService service = new PersonService(); //step1. private static으로 지정해서 객체하나를 생성해놓는다.
	
	private PersonService() {}//step2. private 생성자를 작성해서 객체를 또 생성하지 못하도록 막는다.
	
	public static PersonService getInstance() {//step 3. 위에 만든 객체를 통해 PersonService 클래스안의 기능들을 access할 수 있도록 public static 메소드 정의
		return service;
	}
	
	
	//1. 모든 정보를 출력하는 기능
	public Person[] getAllPerson(Person[] per) {
		Person[] people = new Person[per.length];
		int i=0;
		for (Person p:per) {
			if(p==null) continue;
			people[i++]=p;
			System.out.println(p);
		}
		return people;
	}
	
	//2. 이름으로 사람 검색하기(이름 중복없다는 전제하에)
	public Person findPersonByName(Person[] per, String name) {
		Person person = null;
		for (Person p:per) {
			if(p==null) continue;
			if(p.getName().equals(name)) {
				person = p;
				System.out.println(name+"으로 찾은 사람은: "+person);
			}
		}
		return person;
	}
	
	//3.특정한 사람의 연봉을 반환 
	public int getAnnualSalary(Person[] people,Person person) {
		int annualSalary =0;
		Person p = person;
		for (Person i : people) {
			if(i instanceof Manager) {
				p=i;
				annualSalary=((Manager)p).getSalary()*12+((Manager)p).getBonus();
			}
		}
		return annualSalary;
	}
	
	//4. 총 인건비
	public int getTotalCost(Person[] per) {
		int total = 0;
		int i=0;
		Person[] people = new Person[per.length];
		for (Person p:per) {
			if(p instanceof Manager) {
				people[i++] =p;
				total += getAnnualSalary(per,p);
			}
		}
		return total;
	}
	
}
