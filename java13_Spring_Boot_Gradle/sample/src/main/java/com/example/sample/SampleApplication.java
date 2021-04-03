package com.example.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/** 
 * SampleApplication.java는 spring구성 메인 클래스이다. 
 * 여기에서 application으 시작할 수 있는 main method가 존재한다.
 * main method는 spring boot의 SampleApplication.run() 메소드를 사용하여
 * spring boot project application을 실행할 수 있게 한다.
 * 
 * @SpringBootApplication는 스프링 부트의 핵심 어노테이션
 * includes the following three annotations:
 * @EnableAutoConfiguration : 스프링의 다양한 설정을 자동으로 구성한다.
 * @ComponentScan : 컴포넌트 클래스를 검색하고 검색된 컴포넌트 및 클래스를 스프링 application context에 등록
 * @Configuration : 자바기반 설정 파일(XML설정대신 사용)
 */

//ComponentScan을 사용해서 Controller등 자동 인식 package 설정 추가필요. 
//아래와 같이 com.example package아래 sample.controller.*, validator.* (Controller 클래스들)이 application context에 등록됨.
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.*"})
public class SampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);
	}

}
