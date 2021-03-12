package coll;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CollectionBean {
	//member변수 이름과 text.xml의 property name과 동일해야함
	private List<String> list;
	private Map<String, String> map;
	private Properties proper;
	
	public CollectionBean() {
		System.out.println("CollectionBean 생성자 호출");
	}
	
	public void setList(List<String> list) {
		this.list=list;
		System.out.println(">>> setList() 호출");
	}
	public List<String> getList() {
		return list;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
		System.out.println(">>> setMap() 호출");
	}
	public Map<String, String> getMap() {
		return map;
	}

	public void setProper(Properties proper) {
		this.proper = proper;
	}
	public Properties getProper() {
		return proper;
	}

	
	

	
}
