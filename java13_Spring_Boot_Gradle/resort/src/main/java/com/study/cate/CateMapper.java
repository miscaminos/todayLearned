package com.study.cate;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CateMapper {
	
	int create(CateVO vo);
	CateVO read(int cateno);
	int update(CateVO vo);
	int delete(int cateno);
	List<CateVO> list_by_categrpno(int categrpno);
	List<Categrp_Cate_join> list_join_by_categrpno (int categrpno); 
	int increaseCnt(int cateno);
	int decreaseCnt(int cateno);
	int update_seqno_up(int cateno);
	int update_seqno_down(int cateno);
	int update_visible(CateVO vo);	
	
}
