package practice.spring.model.bbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.model.reply.ReplyMapper;

@Service
public class BbsService {
	@Autowired
	private BbsMapper mapper;
	
	@Autowired
	private ReplyMapper rmapper;
	
	@Autowired
	private BbaDAOJPA jpa;
	
	public void insert(BbsVO vo) {
		jpa.insertBbs(vo);
	}
	
	public void delete(int bbsno) throws Exception{
		
		//1. 댓글들을 지운다
		rmapper.bdelete(bbsno);
		
		//2. 부모글을 지운다
		mapper.delete(bbsno);
	}
	
	
}
