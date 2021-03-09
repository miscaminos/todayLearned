package ioc;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		// 1. Spring container 구동
		// app-context.xml안에 <bean>태그 안에 선언한 내용을 보고 객체를 생성해준다.
		// GenericXmlApplicationContext instance 생성시, 바로 init() 호출 & bean에 정의한 class 변수 초기화
		AbstractApplicationContext factory = 
				new GenericXmlApplicationContext("META-INF/spring/app-context-annotation.xml");
		
		// 2. spring container로 부터 필요한 객체를 요청(lookup)한다
		// getBean으로 가져올때에 object 형태이기때문에, TV 형으로 casting해주어야함.
		//TV tv = (TV)factory.getBean("tv");
		TV tv = (TV)factory.getBean("tv"); 
		//annotation을 사용할때, 이름을 명시하지 않으면, component로 지정한 class이름의 첫글자를 소문자로 한 이름이 자동으로 지정됨
		//이름을 명시했다면, 그 이름을 .getBean("tv")와 같이 사용하면 됨.
		
		//System.out.println("++++++");
		//tv.powerOn();
		//tv.volumeUp();
		//tv.volmeDown();
		//tv.powerOff();
		//System.out.println("++++++");
		tv.powerOn();
		tv.volumeUp();
		tv.volmeDown();
		tv.powerOff();
		
		// 3. spring container 종료
		factory.close();
		

	}

}
