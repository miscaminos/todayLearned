package com.study.categrp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("com.study.categrp.CategrpServiceImpl")
public class CategrpServiceImpl implements CategrpService {
	
	@Autowired
	private CategrpMapper mapper;
	
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
		// TODO Auto-generated method stub
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
