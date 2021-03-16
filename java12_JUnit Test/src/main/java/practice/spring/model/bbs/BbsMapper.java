package practice.spring.model.bbs;

import java.util.List;
import java.util.Map;

public interface BbsMapper {
	//mybatis/bbs.xml와 match되어야함
	//아래 method의 return type은 각 query문 태그의 resultType과, 매개변수는 prameterType과 match된다.
	List<BbsDTO> list(Map map);
	int total(Map map);
	int create(BbsDTO dto);
	int upViewcnt(int bbsno);
	BbsDTO read(int bbsno);
	int update(BbsDTO dto);
	int passCheck(Map map);
	int checkRefnum(int bbsno);
	int delete(int bbsno);
	BbsDTO readReply(int bbsno);
	int upAnsnum(Map map); 
	int createReply(BbsDTO dto);
}
