package com.study.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//아래와같이 @Service annotation을 적어야 객체가 생성된다.
@Service("com.study.model.NoticeServiceImpl")
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper mapper;
	
	@Override
	public int create(NoticeDTO dto) {
		// TODO Auto-generated method stub
		return mapper.create(dto);
	}
	
	@Override
	public int total(Map map) {
		// TODO Auto-generated method stub
		return mapper.total(map);
	}

	@Override
	public List<NoticeDTO> list(Map map) {
		// TODO Auto-generated method stub
		return mapper.list(map);
	}

}
