package com.study.contents;

import java.util.List;
import java.util.Map;

public interface ReplyMapper {
	int create(ReplyDTO dto);
	List<ReplyDTO> list(Map map);
	int total(int bbsno);
	ReplyDTO read(int rnum);
	int update(ReplyDTO dto);
	int delete(int rnum);
	int rcount(int bbsno);
	int bdelete(int bbsno);
}
