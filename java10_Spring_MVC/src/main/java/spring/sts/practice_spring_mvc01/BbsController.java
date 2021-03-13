package spring.sts.practice_spring_mvc01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.model.board.BbsDAO;
import spring.model.board.BbsDTO;
import spring.utility.board.Utility;

//요청 들어올 시, dispatcher servlet이 controller안에 Get/Gost/RequestMapping 중 어느방식으로 실행할지 찾고, method를 찾는다

//컴포넌트 스캔을 위해 애노태이션 선언해야 객체 생성됨 
@Controller
public class BbsController {
	
	//의존하는 객체 주입을 위한 annotation. Injection 받아오려면 참조형 dao에 @Repository를 사용
	@Autowired
	private BbsDAO dao;
	
	//이전 config.properties에서 mapping했던것을 아래와같이 annotation으로 지정한다 (e.g., @GetMpping("/bbs/create")
	//여기 Controller class안에서 command - command handler mapping을 바로 한다.
	//이전 action class를 만들었던것을 method로 지정한다 (e.g., create() 메소드)
	
	@GetMapping("/bbs/create") //"/bbs/create": GetMapping을 요청한 url
	//get방식으로 "/bbs/create"를 요청이 온 경우, @GetMapping annotation으로 mapping을 지정
	private String create() {
		return "/bbs/create"; 
		//view resolver안에서 여기서 반환하는 url을 찾는다.
		//실제 가리키는 파일은 createForm이지만, bbs.xml에서 설정한대로 view name을 적어야해서 "/bbs/create"이다.
	}
	
	//form에서 request객체에 담은 정보들이 들어올것임.
	@PostMapping("/bbs/create")
	//post방식으로 요청이 온 경우, @PostMapping annotation으로 mapping을 지정
	public String create(BbsDTO dto, HttpServletRequest request) {
		String basePath = request.getRealPath("/storage");
		
		String file = null;
		if(dto.getFilenameMF()!=null) {
			dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), basePath));
			dto.setFilesize((int)dto.getFilenameMF().getSize());
		}
		
		boolean flag = dao.create(dto);
		
		if(flag) {
			return"redirect:/bbs/list";
			//여기 문자열의 redirect는 sendRedirect와 동일한 의미. view resolver안에서 /bbs/list를 찾는다.
		}else {
			return "error";
			//view resolver에서 error를 찾는다.
		}
	}
	
	//get인지 post인지 표기하지 않고 하는 방법은 RequestMapping을 사용하는것.
	@RequestMapping("/bbs/list")
	public String list(HttpServletRequest request) {
		// 검색관련------------------------
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		// Utiliy.checkNull을 통해 값이 없어도, 에러 없이 빈 값으로 처리하도록

		if (col.equals("total")) {
			word = "";
		}

		// 페이지관련
		int nowPage = 1;// 현재 보고있는 페이지
		if (request.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		int recordPerPage = 8;// 한페이지당 보여줄 레코드갯수

		// DB에서 가져올 순번
		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		int total = dao.total(col, word);

		List<BbsDTO> list = dao.list(map);

		String paging = Utility.paging(total, nowPage, recordPerPage, col, word);

		// request에 Model사용 결과 담는다
		request.setAttribute("list", list);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("paging", paging);
		return "/bbs/list";
	}
	
	@GetMapping("/bbs/read")
	//read는 get방식만 사용 가능
	public String read(int bbsno, Model model) {
	    dao.upViewcnt(bbsno);
	    BbsDTO dto = dao.read(bbsno);
	    String content = dto.getContent().replaceAll("\r\n", "<br>");  
	    dto.setContent(content);
	    model.addAttribute("dto", dto);
		return "/bbs/read";
	}
	
	@GetMapping("/bbs/update")
	public String update(int bbsno, Model model) {
		model.addAttribute("dto", dao.read(bbsno));
		
		return "/bbs/update";
	}
	
	@PostMapping("/bbs/update")
	public String update(BbsDTO dto, String oldfile, HttpServletRequest request) {
		String basePath = request.getRealPath("/storage");

		if(dto.getFilenameMF()!=null) {
			//만약 oldfile이 존재한다면 oldfile을 지우고 새로 update하려는 file생성할것임.
			if(oldfile!=null)Utility.deleteFile(basePath, oldfile);
			dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), basePath));
			dto.setFilesize((int)dto.getFilenameMF().getSize());
		}
		
		Map map = new HashMap();
		map.put("bbsno", dto.getBbsno());
		map.put("passwd", dto.getPasswd());
		
		//update하기 전, password 체크
		boolean pflag = dao.passCheck(map);
		boolean flag = false;
	
		if(pflag) {
			flag = dao.update(dto);
		}
		if(!pflag) {
			return "/bbs/passwdError";
		}else if(flag) {
			return "redirect: /bbs/list";
		}else {
			return "/bbs/error";
		}
	}
	
	@GetMapping("/bbs/reply")
	public String reply(int bbsno, Model model) {
		
		model.addAttribute("dto",dao.readReply(bbsno));
		return "/bbs/reply";
	}
	
	@PostMapping("/bbs/reply")
	public String reply(BbsDTO dto , HttpServletRequest request) {
		String basePath = request.getRealPath("/storage");
		if(dto.getFilenameMF()!=null) {
			dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), basePath));
			dto.setFilesize((int)dto.getFilenameMF().getSize());
		}
		
		Map map = new HashMap();
		map.put("grpno", dto.getGrpno());
		map.put("ansnum", dto.getAnsnum());
		dao.upAnsnum(map);
		
		boolean flag = dao.createReply(dto);
		
		if(flag) {
			return "redirect:/bbs/list";
			//여기 문자열의 redirect는 sendRedirect와 동일한 의미.
		}else {
			return "/bbs/error";
		}
	}
	
	@GetMapping("/bbs/delete")
	public String delete(int bbsno, Model model) {
		boolean flag = dao.checkRefnum(bbsno);//dao의 checkRefnum: refnum column에 bbsno가 있으면 부모글이라고 인지한다.
		model.addAttribute("flag", flag);
		return "/bbs/delete";
		
	}
	
	@PostMapping("/bbs/delete")
	public String delete(int bbsno, String passwd, String oldfile, HttpServletRequest request) {
		//@RequestParam Map<Integer, String> map 를 매개변수로 사용해서 한번에 map에 가져오는 방법은?? 찾아봐야함.
		String upDir=request.getRealPath("/storage");
		
		Map map = new HashMap();
		map.put("bbsno",bbsno);
		map.put("passwd",passwd);
		
		boolean pflag = dao.passCheck(map);
		boolean flag=false;
		if(pflag) {
			if(oldfile!=null) Utility.deleteFile(upDir, oldfile);
			flag=dao.delete(bbsno);
		}
		if(!pflag) {
			return"/bbs/passwdError";
		}else if(flag) {
			return "redirect: /bbs/list";
		}else {
			return "/bbs/error";
		}
	}
	
	//deleteForm 페이지 요청
	@GetMapping("/bbs/delete_Ajax")
	public String delete_Ajax() {
		return "/bbs/delete_Ajax";		
	}
	
	//Ajax를 위한 데이터 변환처리
	/* Map<String,String>: post방식의 delete_Ajax의 반환타입은 String이 아닌, JSON 형태 data를 담아서 반환할 수 있는 Map형태이다.
	 * ResponseBody: Controller에서 데이터 처리후 자바객체의 값을 JSON형태로 변환해서 리턴한다.
	 * Requestbody: view페이지에서 전달하는 JSON형태의 데이터 문자열 형태로 변환해서 받는다.
	 */
	@PostMapping(value="/bbs/delete_Ajax", produces="application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, String> delete_Ajax(@RequestBody BbsDTO dto, HttpServletRequest request) {
		
		boolean cflag = dao.checkRefnum(dto.getBbsno());
		String upDir=request.getRealPath("/storage");
		
		Map map = new HashMap();
		map.put("bbsno",dto.getBbsno());
		map.put("passwd",dto.getPasswd());
		
		//passwd check
		boolean pflag = false;
		//답변달린게있는지 체크
		boolean flag = false;
		
		//유효성 검사
		if(!cflag) pflag = dao.passCheck(map);
		if(pflag) {
			if(dto.getFilename() !=null) Utility.deleteFile(upDir, dto.getFilename());
			flag=dao.delete(dto.getBbsno());
		}
		
		//반환할 데이터 map2에 넣기
		Map<String,String> map2 = new HashMap<String,String>();       
	    
	    if(cflag) {
	       map2.put("str", "답변있는 글이므로 삭제할 수 없습니다");
	    }else if(!pflag) {
	       map2.put("str","패스워드가 잘못입력되었습니다");
	    }else if(flag) {
	      map2.put("str","삭제 처리되었습니다");
	    }else {
	      map2.put("str","삭제중 에러가 발생했습니다");
	    }
	 
	    return map2;
		
	}
	
}
