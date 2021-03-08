package coll;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionBeanClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractApplicationContext factory = new GenericXmlApplicationContext("app-context2.xml");
		
		CollectionBean coll = (CollectionBean)factory.getBean("coll");
		
		List<String> list = coll.getList();
		for(String li : list) {
			System.out.println(li);
		}
		
		//Map에서는 어떤 object타입이든 다 넣을 수 있다.
		Map<String, String> map = coll.getMap();
		System.out.println(map.entrySet());
		
		//Properties는 문자열만 담을수있다(e.g.,config.properties 파일에 문자열을 가져왔던것처럼)
		Properties proper = coll.getProper();
		System.out.println(proper);
		
		factory.close();
	}

}
