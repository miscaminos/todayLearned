package com.study.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

//NoticeMapper의 method명을 xml mapper의 id값과 일치 시킨다.
//interface이기때문에 구분만의 위해서 @Mapper annotation 사용. 
//객체생성을 목적의 annotation이 아니다.
@Mapper
public interface NoticeMapper {
	int create(NoticeDTO dto);
	List<NoticeDTO> list(Map map);
	int total(Map map);
	
}
