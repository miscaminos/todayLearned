package practice.spring.model.member;

import java.util.List;
import java.util.Map;

public interface MemberMapper {

	int duplicatedId(String id);
	int duplicatedEmail(String email);
	int create(MemberDTO dto);
	int loginCheck(Map<String, String> map);
	String getGrade(String id);
	MemberDTO read(String id);
	int update(MemberDTO dto);
	int total(Map map);
	List<MemberDTO> list(Map map);
	int updateFile(MemberDTO dto);

}
