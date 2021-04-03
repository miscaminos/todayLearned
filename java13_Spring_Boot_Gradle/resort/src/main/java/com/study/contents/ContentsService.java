package com.study.contents;

import java.util.HashMap;
import java.util.List;

public interface ContentsService {
	  int create(ContentsVO contentsVO);

	  List<ContentsVO> list_by_cateno(int cateno);

	  int search_count(HashMap<String, Object> hashMap);

	  List<Contents_UsersVO> list_by_cateno_search_paging_join(HashMap<String, Object> map);

	  ContentsVO read(int contentsno);

	  ContentsVO read_update(int contentsno);

	  int update(ContentsVO contentsVO);

	  int passwd_check(HashMap<String, Object> hashMap);

	  int delete(int contentsno);

	  int img_create(ContentsVO contentsVO);

	  int img_update(ContentsVO contentsVO);

	  int img_delete(ContentsVO contentsVO);

	  int total_count();

}
