package practice.spring.model.reply;

import java.util.List;
import java.util.Map;

public interface ReplyMapper {
	//reply.xml에 <insert id="create" parameter="ReplyDTO">와 match
	   int create(ReplyDTO replyDTO);
	   List<ReplyDTO> list(Map map);
	   int total(int bbsno);
	   ReplyDTO read(int rnum);
	   int update(ReplyDTO dto);
	   int delete(int rnum);
	   int rcount(int bbsno);
}
