package practice.spring.model.bbs;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class BbaDAOJPA {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insertBbs(BbsVO vo) {
		System.out.println("=======>JPA로 insertBbs()기능 처리");
		em.persist(vo);
	}
}
