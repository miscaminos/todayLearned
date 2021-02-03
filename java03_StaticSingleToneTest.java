package exam.test;
/*
 * 싱글톤
 * ::
 * 하나의 클래스로부터 오직 단 하나의 객체만 생성되도록 작성하는 코드 패턴
 * ::
 * 작성 방법
 * 1. 클래스 안에서 일단 하나의 객체는 생성해 놓는다. -- "private static"으로 지정해서 생성함.
 * 2. 다른 클래스에서는 객체 생성을 못하도록 막아놓는다. -- "private 생성자()" private이란 -자기클래스말고는 생성못하게 막는것임.
 * 3. 하나 만들어 놓은 객체를 여기저기 가져다 쓰도록 public한 기능을 만든다. -->public static
 * 
 */

class Service{
	private static Service service = new Service(); //1
	private Service() { //2
		System.out.println("Service Instance Creating. Using SingleTone.");
	}
	public static Service getInstance() { //3
		return service;
	}
}


public class java03_StaticSingleToneTest{

	public static void main(String[] args) {
		//Service s1 = new Service(); <--에러남..
		//만들어놓은 것을 받아서 사용하는 방법밖에는 없다.
		
	Service service1 = Service.getInstance();
	Service service2 = Service.getInstance();
	Service service3 = Service.getInstance();

	System.out.println(service1);
	System.out.println(service2);
	System.out.println(service3);// 다 같은 객체다!!!
	
	}

}
