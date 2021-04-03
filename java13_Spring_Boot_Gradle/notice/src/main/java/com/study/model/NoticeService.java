package com.study.model;

import java.util.List;
import java.util.Map;

/* - Service 인터페이스와 ServiceImpl클래스를 만들어 사용한다.
 * - 인터페이스와 구현클래스로 분리하는 장점이 있음.
 * 1. 느슨한 결합(loose coupling)으로 기능간의 의존관계 최소화
 * 2. 기능변화에 대한 최소한의 수정에 따른 유연성 최대화
 * 3. 모듈화를 통한 높은 재사용성
 * 4. 스프링의 IOC/DI 사용의 활용의 극대화 
 */

//ServiceImpl에서만 @Service annotation을 사용함. 여기는 interface라서 annotation없음.
//객체생성하지않기 때문에 annotation 사용X
public interface NoticeService {
	int create(NoticeDTO dto);
	List<NoticeDTO> list(Map map);
	int total(Map map);
}
