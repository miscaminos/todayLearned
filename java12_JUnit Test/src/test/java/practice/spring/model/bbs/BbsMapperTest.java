package practice.spring.model.bbs;


import static org.junit.Assert.assertTrue;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
//이미 src이기때문에, 절대경로말고, src부터 경로적어라


public class BbsMapperTest {
	//logger를 통해서 실행내용 기록
	private static final Logger logger = LoggerFactory.getLogger(BbsMapperTest.class);
	
	@Autowired 
	private BbsMapper mapper;
	
	@Test
	public void testMapper() {
		logger.info("mapper:"+mapper.getClass().getName());
		
	}
	
	@Test 
	public void testCreate() {
		BbsDTO dto = new BbsDTO();
		dto.setWname("Arinana");
		dto.setTitle("Thank you Next");
		dto.setContent("The best song of the year");
		dto.setPasswd("1234");
		dto.setFilename(" ");
		dto.setFilesize(0);
		assertTrue(mapper.create(dto)>0);
		
	}
	
}
