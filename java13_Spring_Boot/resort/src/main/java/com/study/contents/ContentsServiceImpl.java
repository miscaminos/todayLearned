package com.study.contents;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.utility.Utility;

@Service("com.study.contents.ContentsServiceImpl")
public class ContentsServiceImpl implements ContentsService{
	
	@Autowired
	private ContentsMapper mapper;

	@Override
	public int create(ContentsVO contentsVO) {
		// TODO Auto-generated method stub
		return mapper.create(contentsVO);
	}

	@Override
	public List<ContentsVO> list_by_cateno(int cateno) {
		// TODO Auto-generated method stub
		return mapper.list_by_cateno(cateno);
	}

	@Override
	public int search_count(HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		return mapper.search_count(hashMap);
	}

	@Override
	public List<Contents_UsersVO> list_by_cateno_search_paging_join(HashMap<String, Object> map) {
		int beginOfPage = ((Integer)map.get("nowPage") - 1) * Contents.RECORD_PER_PAGE;
		
		int startNum = beginOfPage + 1;
		int endNum = beginOfPage + Contents.RECORD_PER_PAGE;
		
		map.put("startNum", startNum);
		map.put("endNum", endNum);

		return mapper.list_by_cateno_search_paging_join(map);
	}

	@Override
	public ContentsVO read(int contentsno) {

		ContentsVO vo = mapper.read(contentsno);
		vo.setSize1_label(Utility.unit(vo.getSize1()));//자동형변환
		
		return vo;
	}

	@Override
	public ContentsVO read_update(int contentsno) {
		// TODO Auto-generated method stub
		return mapper.read(contentsno);
	}

	@Override
	public int update(ContentsVO contentsVO) {
		// TODO Auto-generated method stub
		return mapper.update(contentsVO);
	}

	@Override
	public int passwd_check(HashMap<String, Object> hashMap) {
		// TODO Auto-generated method stub
		return mapper.passwd_check(hashMap);
	}

	@Override
	public int delete(int contentsno) {
		// TODO Auto-generated method stub
		return mapper.delete(contentsno);
	}

	@Override
	public int img_create(ContentsVO contentsVO) {
		// TODO Auto-generated method stub
		return mapper.update_img(contentsVO);
	}

	@Override
	public int img_update(ContentsVO contentsVO) {
		// TODO Auto-generated method stub
		return mapper.update_img(contentsVO);
	}

	@Override
	public int img_delete(ContentsVO contentsVO) {
		// TODO Auto-generated method stub
		return mapper.update_img(contentsVO);
	}

	@Override
	public int total_count() {
		// TODO Auto-generated method stub
		return mapper.total_count();
	}

}
