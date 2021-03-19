package com.example.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/** @SpringBootApplication는 스프링 부트의 핵심 어노테이션 - 아래 스프링 부트 어노테이션으로 구성됨.
 * includes the following three Spring boot annotations:
 *
 * @EnableAutoConfiguration : 스프링의 다양한 설정을 자동으로 구성한다.
 * @ComponentScan : 컴포넌트 클래스를 검색하고 검색된 컴포넌트 및 클래스를
 *                        스프링 application context에 등록
 * @Configuration : 자바기반 설정 파일(XML설정대신 사용)
 *
 * - main메소드는 스프링 부트의 SampleApplication.run() 메소드를 사용하여 스프링
 * 부트 프로젝트 애플리케이션을 실행할 수 있게 한다.
 */


//ComponentScan을 사용해서 Controller등 자동 인식 package 설정 추가필요. 
//아래와 같이 com.example package아래 sample.*, validator.* 산하 Controller 클래스들이 application context에 등록됨.
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.*"})
public class SampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}

}
