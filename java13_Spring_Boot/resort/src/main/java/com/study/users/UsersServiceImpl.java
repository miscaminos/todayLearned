package com.study.users;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("com.study.users.UsersServiceImpl")
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersMapper mapper;

	@Override
	public int create(UsersVO vo) {
		// TODO Auto-generated method stub
		return mapper.create(vo);
	}

	@Override
	public UsersVO read(int usersno) {
		// TODO Auto-generated method stub
		return mapper.read(usersno);
	}

	@Override
	public int update(UsersVO vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int delete(int usersno) {
		// TODO Auto-generated method stub
		return mapper.delete(usersno);
	}

	@Override
	public List<UsersVO> list() {
		// TODO Auto-generated method stub
		return mapper.list();
	}

	@Override
	public int checkID(String id) {
		// TODO Auto-generated method stub
		return mapper.checkID(id);
	}

	@Override
	public UsersVO readById(String id) {
		// TODO Auto-generated method stub
		return mapper.readById(id);
	}

	@Override
	public int passwd_check(Map map) {
		// TODO Auto-generated method stub
		return mapper.passwd_check(map);
	}

	@Override
	public int passwd_update(Map map) {
		// TODO Auto-generated method stub
		return mapper.passwd_update(map);
	}

	@Override
	public int login(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.login(map);
	}

	@Override
	public boolean isMember(HttpSession session) {
		boolean sw = false;
		String id = (String)session.getAttribute("id");
		if(id != null) sw = true;
		
		return sw;
	}

}
