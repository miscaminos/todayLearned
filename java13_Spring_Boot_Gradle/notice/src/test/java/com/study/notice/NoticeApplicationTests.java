package com.study.notice;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeApplicationTests {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void tetSqlSession() throws Exception{
		System.out.println(sqlSession);
		
	}

}
