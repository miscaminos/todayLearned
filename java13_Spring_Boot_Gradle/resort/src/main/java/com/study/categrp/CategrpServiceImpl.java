package com.study.categrp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.cate.CateMapper;
import com.study.contents.ContentsMapper;

@Service("com.study.categrp.CategrpServiceImpl")
public class CategrpServiceImpl implements CategrpService {
	
	@Autowired
	private CategrpMapper mapper;
	
	@Autowired
	private CateMapper catemapper;
	
	@Autowired 
	private ContentsMapper contentsmapper;
	
	@Override
	public int create(CategrpVO vo) {
		// TODO Auto-generated method stub
		return mapper.create(vo);
	}

	@Override
	public List<CategrpVO> list_seqno_asc() {
		// TODO Auto-generated method stub
		return mapper.list_seqno_asc();
	}

	@Override
	public CategrpVO read(int categrpno) {
		// TODO Auto-generated method stub
		return mapper.read(categrpno);
	}

	@Override
	public int update(CategrpVO vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int delete(int categrpno) {
		//delete에 두가지 option이 있음:
		// 1. table생성시, on delete cascade적용하여 자동으로 자식 table의 tuples 삭제
		// 2. 각각의 자식 table tuple을 조회해서 가장 아래 자식 테이블부터 순차적으로 해당 tuples 삭제
		
		//2번 구현:
		//List<Integer> list = catemapper.readCate(categrpno);
		//List<Integer> list2 = contentsmapper.readContents(cateno);
		//select로 categrpno에 해당하는 cate tuple들,
		//cate tuple들에 해당하는 contents ruple들을 골라와서
		//부모자식 relation의 hierarchy에서 가장 아래부분의 tuples부터 삭제
		//최종적으로 마지막 categrpno에 해당하는 tuple 삭제
		return mapper.delete(categrpno);
		
	}

	@Override
	public int update_seqno_up(int categrpno) {
		// TODO Auto-generated method stub
		return mapper.update_seqno_up(categrpno);
	}

	@Override
	public int update_seqno_down(int categrpno) {
		// TODO Auto-generated method stub
		return mapper.update_seqno_down(categrpno);
	}

	@Override
	public int update_visible(Map map) {
		String visible = (String)map.get("visible");
		
		if(visible.toUpperCase().equals("Y")) {
			map.put("visible", "N");
		}else {
			map.put("visible", "Y");
		}
		return mapper.update_visible(map);
	}

}
