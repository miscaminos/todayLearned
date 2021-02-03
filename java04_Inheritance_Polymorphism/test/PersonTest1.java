package com.encore.test;

import com.encore.child.Manager;
import com.encore.child.Student;
import com.encore.parent.Person;
import com.encore.util.MyDate;
/*
 * Polymorphism
 * 하나의 타입으로 --- Super Type
 * 여러개의 객체를 생성하는 기법 - Sub Class Creating..
 * 
 */
public class PersonTest1 {

	public static void main(String[] args) {
		//부모타입의 자식을 만든다.
		
		Person s1 = new Student("Student1",new MyDate(1990,10,4),"NY","Data Analysis");
		Person s2= new Student("Student2",new MyDate(1989,10,4),"NY","Python");
		Person s3= new Student("Student3",new MyDate(1995,10,4),"Texas","AI");
		Person m1 = new Manager("Manager1",new MyDate(1980,3,2),"Texas","Marketing",1,2);
		
		//Polymorphism으로 객체를 생성하면 아래 두가지 변화를 반드시 이해해야 합니다.
				
		//1.Manager1의 부서를 IT부서로 변경하시고 다시 정보를 확인하세요 - Object casting
		((Manager)m1).setDept("IT");
		System.out.println(m1);
		
		//2.각각 객체 정보를 출력해보세요. - 
		//Questions:: Person(부모) type의 객체를 사용해서 호출하는데 왜 자식클래스에서 override했던 .toString()이 호출될까?
		//--> Virtual Method Invocation이 수행되기때문이다.
		Person[] p = {s1,s2,s3,m1};
		for (Person i:p) System.out.println(i);
		
		/* Virtual Method Invocation(virtual Method= 가상 method= 여기에서는, 자식 메소드)
		 * 상속관계에서 override된 메소드에서만! 나타나는 원리이다. Java는 2단계를 거치는데(compile,run)
		 * 1) compile 시점의 method - 부모의 메소드를 호출: toString()
		 * 2) runtime(실행) 시점의 method - 자식의 메소드를 호출: toString() <--자식메소드 which is overridden version of 부모의 메소드
		 * 상속관계에서는 자식이 (1)부모로부터 물려받고, (2)자식만의 추가/변경사항이 만들어져야하기때문에 이런 원리가 가능한것같다.
		 */
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(m1);
		
		System.out.println(m1.toString());//자식의 .toString()이지만, override되어 부모와 연결되있기때문에 "가상의"자식메소드 호출가능
		//System.out.println(m1.getDept()); <--이거는 error난다 .getDept()는 자식만의 method이기때문에. 
		System.out.println(((Manager)m1).getDept());//
	}

}
