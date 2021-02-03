package board.member;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

//dao interface 메소드 선언만 하면 됨.
//dao interface ==> mapper interface

public interface MemberMapper {
	void addMember(Member m);
	Member getMember(String id);
	ArrayList<Member> getMembers();
	void editMember(Member m);
	void delMember(String id);
}
