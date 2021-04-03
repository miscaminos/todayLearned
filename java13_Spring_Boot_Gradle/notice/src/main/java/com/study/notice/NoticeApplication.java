package com.study.notice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//com.study.notice package밖에있는 package를 사용하려면 아래와같이 표기해야함
//com.study로 시작하는 package는 다 component scan을 해달라는 요청:
@ComponentScan(basePackages={"com.study.*"})
public class NoticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoticeApplication.class, args);
	}

}
