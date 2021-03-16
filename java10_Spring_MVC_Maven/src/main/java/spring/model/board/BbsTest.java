package spring.model.board;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BbsTest {

  public static void main(String[] args) {
    BbsDAO dao = new BbsDAO();
    create(dao);
    //list(dao);

  }

  private static void update(BbsDAO dao) {
    BbsDTO dto = dao.read(1);
    dto.setWname("아로미");
    dto.setTitle("제목변경");
    dto.setContent("내용변경");
    if (dao.update(dto)) {
      p("성공");
      dto = dao.read(1);
      p(dto);
    } else {
      p("실패");
    }

  }

  private static void list(BbsDAO dao) {
	  
	// Map map = new HashMap(); 도 가능
	// 위와같은 방법은 generic을 사용하지 않고 (타입을 선언하지 않은)객체를 넣을 HashMap을 생성한것임.
	  
	//아래는 generic으 사용한것임
    Map <String, Object> map = new HashMap(); 
    map.put("col", "wname");
    map.put("word", "lauv");
    map.put("sno", 1);
    map.put("eno", 5);
    
	List<BbsDTO> list = dao.list(map);
    Iterator<BbsDTO> iter = list.iterator();
    while (iter.hasNext()) {
      BbsDTO dto = iter.next();
      p(dto);
    }

  }

  private static void p(BbsDTO dto) {
    p("목록=====================");
    p("번호:" + dto.getBbsno());
    p("성명:" + dto.getWname());
    p("제목:" + dto.getTitle());
    p("조회수:" + dto.getViewcnt());
    p("날짜:" + dto.getWdate());
    p("전체--------------------");
    p("내용:" + dto.getContent());
    p("grpno:" + dto.getGrpno());
    p("indent:" + dto.getIndent());
    p("ansnum:" + dto.getAnsnum());

  }

  private static void create(BbsDAO dao) {
    BbsDTO dto = new BbsDTO();
    dto.setWname("김길동");
    dto.setTitle("게시판 제목");
    dto.setContent("게시판 내용");
    dto.setPasswd("1234");
    dto.setFilename("test.txt");
    dto.setFilesize(10);
    if (dao.create(dto)) {
      p("성공");
    } else {
      p("실패");
    }

  }

  private static void p(String string) {
    System.out.println(string);

  }

}