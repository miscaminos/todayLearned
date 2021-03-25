package com.study.users;

import java.util.List;
import java.util.Map;

public interface UsersMapper {
	int create(UsersVO vo);
	UsersVO read(int usersno);
	int update(UsersVO vo);
	int delete(int usersno);
	List<UsersVO> list();
	int checkID(String id);
	UsersVO readById(String id);
	int passwd_check(Map map);
	int passwd_update(Map map);
	int login(Map map);
}
