package practice.spring.mvc;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.model.bbs.BbsDTO;
import spring.model.bbs.BbsMapper;
import spring.model.bbs.BbsService;
import spring.model.bbs.BbsVO;
import spring.model.reply.ReplyMapper;
import spring.utility.webtest.Utility;

//요청 들어올 시, dispatcher servlet이 controller안에 get/post 어느방식으로 실행할지 찾고, method를 찾는다

//컴포넌트 스캔을 위해 애노태이션 선언해야 객체 생성됨 
@Controller
public class BbsController {
	
	//Injection 받아오려면 참조형 dao에 @Repository 
	//@Autowired
	//private BbsDAO dao;
	
	@Autowired
	private BbsMapper mapper;
	
	@Autowired
	private ReplyMapper rmapper;
	
	//AOP 실행을 위해 추가함. BbsVO(JPA entity)를 다루는 Service 클래스
	@Autowired
	private BbsService service; 

	
	//이전 config.properties에서 mapping했던것을 아래와같이 annotation으로 지정한다 (e.g., @GetMpping("/bbs/create")
	//여기 Controller class안에서 command - command handler mapping을 바로 한다.
	//이전 action class를 만들었던것을 method로 지정한다 (e.g., create() 메소드)
	
	
	@PostMapping("/bbs/createJPA")
	public String create(BbsVO dto, HttpServletRequest request) {
		String basepath = request.getRealPath("/storage");
		if(dto.getFilenameMF()!=null) {
			dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), basepath));
			dto.setFilesize((int)dto.getFilenameMF().getSize());
		}
		try {
			service.insert(dto);
			return "redirect:./list";
		}catch(Exception e){
			return "./error";
		}
		
	}
	
	@GetMapping("/bbs/create") //Controller로 요청이 들어온 url: "/bbs/create"
	//get방식으로 "/bbs/create"를 요청하는 경우에만 @GetMapping annotation으로 mapping을 지정할 수 있다
	private String create() {
		return "/bbs/create"; 
		//실제 가리키는 파일은 createForm이지만, bbs.xml에서 적은 view name(definition name)을
		//적어야해서 "/bbs/create"이다. (tiles를 관리하는 webapp/WEB-INF/spring/bbs.xml 파일에 설정됨)
	}
	
	//form에서 request객체에 담은 정보들이 들어올것임.
	@PostMapping("/bbs/create")
	public String create(BbsDTO dto, HttpServletRequest request) {
		String basePath = request.getRealPath("/storage");
		if (dto.getFilenameMF() != null) {
			dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), basePath));
			dto.setFilesize((int) dto.getFilenameMF().getSize());
		}
		//OR code를 더 간단하게 만들기 위해, 위 내용을 한줄로 간략하게적어서 setFilename을 지정 할 수도 있다.
		//dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(),basePath));
		
		boolean flag = false;
		
		int cnt = mapper.create(dto);
		if(cnt>0)flag=true;
		
		if(flag) {
			return"redirect:/bbs/list";
			//여기 문자열의 redirect는 sendRedirect와 동일한 의미.
		}else {
			return "/bbs/error";
		}
	}
	
	//get인지 post인지 표기하지 않고 하는 방법은 RequestMapping을 사용하는것.
	@RequestMapping("/bbs/list")
	public String list(HttpServletRequest request) {
		// 검색관련------------------------
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));

		if (col.equals("total")) {
			word = "";
		}

		// 페이지관련-----------------------
		int nowPage = 1;// 현재 보고있는 페이지
		if (request.getParameter("nowPage") != null) {
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		int recordPerPage = 8;// 한페이지당 보여줄 레코드갯수

		// DB에서 가져올 순번-----------------
		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		int total = mapper.total(map);

		//List<BbsDTO> list = dao.list(map); mybatis를 사용하면 더이상 dao를 사용하지않고 mapper를 사용한다.
		List<BbsDTO> list = mapper.list(map);

		String paging = Utility.paging(total, nowPage, recordPerPage, col, word);

		// request에 Model사용 결과 담는다
		request.setAttribute("list", list);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("paging", paging);
		request.setAttribute("rmapper", rmapper);

		return "/bbs/list";
	}
	
	@GetMapping("/bbs/read")
	//read는 get방식만 사용 가능
	public String read(int bbsno, Model model, HttpServletRequest request) {
		mapper.upViewcnt(bbsno);

		BbsDTO dto = mapper.read(bbsno);

		String content = dto.getContent().replaceAll("\r\n", "<br>");

		dto.setContent(content);

		model.addAttribute("dto", dto);
		
		/*댓글관련 시작*/
		int nPage=1;
		if(request.getParameter("nPage")!=null) {
			nPage = Integer.parseInt(request.getParameter("nPage"));
		}
		int recordPerPage = 3;
		
		int sno = ((nPage-1)*recordPerPage)+1; //0이 아닌 1페이지 부터
		int eno = nPage * recordPerPage;
		
		Map map = new HashMap();
		map.put("sno", sno);
		map.put("eno", eno);
		map.put("nPage", nPage);
		
		model.addAllAttributes(map);
		/*댓글관련 끝*/
		
		return "/bbs/read";
	}
	
	@GetMapping("/bbs/update")
	public String update(int bbsno, Model model) {
		model.addAttribute("dto",mapper.read(bbsno));
		return "/bbs/update";
	}
	
	@PostMapping("/bbs/update")
	public String update(BbsDTO dto, String oldfile, HttpServletRequest request) {
		String basePath = request.getRealPath("/storage");
		//새로 업로드할 파일의 경로 확인
		System.out.println("basePath:"+basePath);
		if (dto.getFilenameMF() != null) {
			if(oldfile!=null)Utility.deleteFile(basePath,oldfile);
			dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), basePath));
			dto.setFilesize((int) dto.getFilenameMF().getSize());
		}
		
		Map map = new HashMap();
		map.put("bbsno",dto.getBbsno());
		map.put("passwd", dto.getPasswd());
		
		//update하기 전, password 체크
		boolean pflag = false;
		int cnt = mapper.passCheck(map);
		if(cnt>0)pflag=true;
		boolean flag = false;
        
		if (pflag) {
			int cnt2 = mapper.update(dto);
			if(cnt2>0)flag=true;
		} 

		if (!pflag) {
			return "/bbs/passwdError";
		}else if(flag) {
			return "redirect:/bbs/list";
		}else {
			return "/bbs/error";
		}
	}
	
	@GetMapping("/bbs/reply")
	public String reply(int bbsno, Model model) {
		
		model.addAttribute("dto",mapper.readReply(bbsno));
		return "/bbs/reply";
	}
	
	@PostMapping("/bbs/reply")
	public String reply(BbsDTO dto , HttpServletRequest request) {
		String basePath = request.getRealPath("/storage");
		if (dto.getFilenameMF() != null) {
			dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), basePath));
			dto.setFilesize((int) dto.getFilenameMF().getSize());
		}
		Map map = new HashMap();
		map.put("grpno", dto.getGrpno());
		map.put("ansnum", dto.getAnsnum());
		
		mapper.upAnsnum(map);
		boolean flag = false;
		int cnt = mapper.createReply(dto);
		if(cnt>0) flag = true;

		if (flag) {
			return "redirect:/bbs/list"; // 재요청
			//여기 문자열의 redirect는 sendRedirect와 동일한 의미.
		} else {
			return "/bbs/error";
		}
		
	}
	
	@GetMapping("/bbs/delete")
	public String delete(int bbsno, Model model) {
		//dao의 checkRefnum: refnum column에 bbsno가 있으면 부모글이라고 인지한다.
		boolean flag = false;
		int cnt = mapper.checkRefnum(bbsno);
		if(cnt>0) flag=true;
		model.addAttribute("flag",flag);
		
		//view resolver 안에서 delete를 찾는다.
		return "/bbs/delete";	
	}
	
	@PostMapping("/bbs/delete")
	public String delete(int bbsno, String passwd, String oldfile, HttpServletRequest request) {
		//RequestParam Map<Integer, String> map 를 매개변수로 사용해서 한번에 map에 가져오는 방법은?? 찾아봐야함.
		
		String upDir = request.getRealPath("/storage");
		Map map = new HashMap();
		map.put("bbsno", bbsno);
		map.put("passwd", passwd);
		int cnt = mapper.passCheck(map);
		
		String url = "/bbs/passwdError";
		
		if(cnt>0) {
			try {
				service.delete(bbsno);
				url = "redirect:/bbs/list";
				if(oldfile!=null) {
					Utility.deleteFile(upDir, oldfile);
				}
			}catch(Exception e) {
				e.printStackTrace();
				url = "/bbs/error";
			}
		}
		return url;
		
//		boolean pflag = false;
//		if(cnt>0)pflag = true;
//		boolean flag = false;
//		if(pflag) {
//			if(oldfile!=null)Utility.deleteFile(upDir, oldfile);
//			int cnt2 = mapper.delete(bbsno);
//			if(cnt2>0)flag=true;
//		}
//		
//		if(!pflag) {
//			return "/bbs/passwdError";
//		}else if(flag) {
//			return "redirect:/bbs/list";
//		}else {
//			return "/bbs/error";
//		}
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
		
		boolean cflag = false;
		int cnt = mapper.checkRefnum(dto.getBbsno());
		if(cnt>0)cflag = true;
		
		String upDir = request.getRealPath("/storage");
		Map map = new HashMap();
		map.put("bbsno", dto.getBbsno());
		map.put("passwd", dto.getPasswd());
		
		boolean pflag = false;
		boolean flag = false;
		
		if(!cflag) {
			int cnt2 = mapper.passCheck(map);
			if(cnt2>0)pflag = true;
		}
		if(pflag) {
			if(dto.getFilename() !=null)Utility.deleteFile(upDir, dto.getFilename());
			int cnt3 = mapper.delete(dto.getBbsno());
			if(cnt3>0)flag = true;
		}
		
		Map<String,String> map2 = new HashMap<String,String>();       
	    
	    if(cflag) {
	       map2.put("str", "답변있는 글이므로 삭제할 수 없습니다");
	       map2.put("color", "blue");
	    }else if(!pflag) {
	       map2.put("str","패스워드가 잘못입력되었습니다");
	       map2.put("color", "blue");
	    }else if(flag) {
	      map2.put("str","삭제 처리되었습니다");
	      map2.put("color", "blue");
	    }else {
	      map2.put("str","삭제중 에러가 발생했습니다");
	      map2.put("color", "blue");
	    }
	     return map2;
	}
	
	@GetMapping("/bbs/fileDown")
	public void fileDown(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		//또는 request.getRealPath(null)로 진행해도됨
		ServletContext ctx = request.getSession().getServletContext();
		//다운받을 파일의 절대경로
		String dir = ctx.getRealPath(request.getParameter("dir"));
		System.out.println("dir:"+ dir);
		String filename = request.getParameter("filename");
		
		//파일을 byte array로 변환해서 내보낸다
		byte[] files = FileUtils.readFileToByteArray(new File(dir,filename));
		
		//response왈 "file이 하나 올건데, 이건 무조건 다운로드해줘! 그리고 혹시 한글표기가 필요하면 unicode(UTF-8)로 적용해줘!!"
		//browser에게 전달하는 header값을 아래와 같이 변경해서 알려주는 것임.
		//octet와 attachment를 사용해서 파일 다운받을시 save-as가 가능하도록 한다.
		response.setHeader("Content-disposition", "attachment; fileName=\""+
			    URLEncoder.encode(filename,"UTF-8")+"\";");
		
		//Content-Transfer-Encoding : 전송 데이타의 body를 인코딩한 방법을 표시함.
	    response.setHeader("Content-Transfer-Encoding", "binary");
	    /**
	     * Content-Disposition가 attachment와 함게 설정되었다면 
	     * 'Save As'로 파일을 제안하는지 여부에 따라 브라우저가 실행한다.
	     */
	    response.setContentType("application/octet-stream");
	    response.setContentLength(files.length);
	    response.getOutputStream().write(files);
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
	}
	
}
