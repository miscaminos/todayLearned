package board.member;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import resources.Factory;


public class MemberService {
	
	private SqlSessionFactory sqlSessionFactory;
	
	public MemberService() {
		sqlSessionFactory = Factory.getSqlSessionFactory();
	}
	
	public void addMember(Member m){
		//SqlSession 객체를 통해 DB와의 연결을 형성 (JDBC 사용할때에 Connection객체과 비슷함)
		//서버에 접속한 상태 만들기위함
		SqlSession session = sqlSessionFactory.openSession();
		MemberMapper mapper = (MemberMapper) session.getMapper(MemberMapper.class);
		//openSession()과 getMapper()는 어디에 구현되어있지?
		//openSession()는 SqlSession 객체를 반환
		//getMapper()는 뭐를 반환하지?
		mapper.addMember(m);
		session.commit();
		session.close();
	}
	
	public Member getMember(String id){
		SqlSession session = sqlSessionFactory.openSession();
		MemberMapper mapper = (MemberMapper) session.getMapper(MemberMapper.class);
		Member m = mapper.getMember(id);
		session.close();
		return m;
	}
	
	public ArrayList<Member> getMembers(){
		SqlSession session = sqlSessionFactory.openSession();
		MemberMapper mapper = (MemberMapper) session.getMapper(MemberMapper.class);
		ArrayList<Member> list = mapper.getMembers();
		session.close();
		return list;
	}
	
	public void editMember(Member m){
		SqlSession session = sqlSessionFactory.openSession();
		MemberMapper mapper = (MemberMapper) session.getMapper(MemberMapper.class);
		mapper.editMember(m);
		session.commit();
		session.close();
	}
	
	public void delMember(String id){
		SqlSession session = sqlSessionFactory.openSession();
		MemberMapper mapper = (MemberMapper) session.getMapper(MemberMapper.class);
		mapper.delMember(id);
		session.commit();
		session.close();
	}
}

