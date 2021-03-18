package practice.spring.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.model.member.MemberDTO;
import spring.model.member.MemberMapper;
import spring.utility.webtest.Utility;

@Controller
public class MemberController {
	
	@Autowired
	private MemberMapper mapper;
	
	@PostMapping("/member/updateFile")
	public String updateFile(MemberDTO dto, String oldfile, HttpServletRequest request) {
		
		String basePath = request.getRealPath("/storage");
		
		//확인용
		System.out.println("fnameMF:"+dto.getFnameMF());
		System.out.println("fname:"+dto.getFname());
		System.out.println("basePath:"+basePath);
		
		if (dto.getFnameMF() != null) {
			if(oldfile!=null)Utility.deleteFile(basePath,oldfile);
			dto.setFname(Utility.saveFileSpring(dto.getFnameMF(), basePath));
		}

		int cnt = mapper.updateFile(dto);
		boolean flag = false;
		
		if(cnt>0) {
			flag=true;
			return "redirect:/member/read";
		}
		else {
			return "/member/errorMsg";
		}
	}
	
	@GetMapping("/member/updateFileForm")
	public String updateFile(String id, Model model) {
		MemberDTO dto = mapper.read(id);
		model.addAttribute("dto", dto);
		
		return "/member/updateFile";
	}
	
	
	@RequestMapping("/admin/list")
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
		int recordPerPage = 4;// 한페이지당 보여줄 레코드갯수

		// DB에서 가져올 순번-----------------
		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);

		int total = mapper.total(map);

		// List<BbsDTO> list = dao.list(map);
		List<MemberDTO> list = mapper.list(map);

		String paging = Utility.paging(total, nowPage, recordPerPage, col, word);

		// request에 Model사용 결과 담는다
		request.setAttribute("list", list);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("paging", paging);

		return "/member/list";
	}
	
	
	@PostMapping("/member/update")
	public String update(MemberDTO dto, Model model) {
		int cnt = mapper.update(dto);
		
		if(cnt==1) {
			model.addAttribute("id", dto.getId());
			return "redirect:./read";
		}
		else {
			return "error";
		}
	}
	
	@GetMapping("/member/update")
	public String update(String id, Model model, HttpSession session) {
		//read에서 정보수정으로 들어가지않고, top 메뉴에서 바로 정보수정으로 들어온다면, id가 비어있기때문에
		//아래와 같이 if(id==null) 경우를 고려해서 session의 getAttribute("id")를 id를 가져온다.
		
		//BUT 관리자인 경우, 본인 session id가 아닌 여러 사용자의 id를 사용할 것이기때문에
		//read에서 조회하고있는 사용자의 id를 가져와야한다.
		if(id==null) {
			id = (String)session.getAttribute("id");
		}
		MemberDTO dto = mapper.read(id);
		model.addAttribute("dto",dto);
		return "/member/update";
	}
	
	@GetMapping("/member/read")
	public String read(String id, Model model, HttpSession session) {
		if(id==null) {
			id = (String)session.getAttribute("id");
		}
		MemberDTO dto = mapper.read(id);
		model.addAttribute("dto",dto);
		return "/member/read";
	}
	
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		//각각의 attribute를 지운다.
		//session.removeAttribute("id");
		//session.removeAttribute("grade");
		
		//또는 invalidate() 메소드 하나로 다 지울 수 있음!
		session.invalidate();
		
		return "redirect:/";
	}
		
	@PostMapping("/member/login")
	public String login(@RequestParam Map<String, String> map, 
			HttpSession session, HttpServletResponse response,
			HttpServletRequest request, Model model) {
		int cnt = mapper.loginCheck(map);
		if(cnt>0) { //회원인경우.
			String grade = mapper.getGrade(map.get("id"));
			session.setAttribute("id", map.get("id"));
			session.setAttribute("grade", grade);
			//cookie 저장: id저장여부 및 id
			Cookie cookie = null;
			String c_id = request.getParameter("c_id");
			if(c_id != null) {
				//id저장여부를 cookie에 저장
				cookie = new Cookie("c_id","Y");
				//cookie를 저장할 시간 지정:1년(in seconds)
				cookie.setMaxAge(60*60*24*365);
				//요청지(client 브라우져 컴) 쿠키 저장
				response.addCookie(cookie);
				
				//id를 cookie에 저장
				cookie = new Cookie("c_id_val",map.get("id"));
				cookie.setMaxAge(60*60*24*365);
				response.addCookie(cookie);
			}
			else {
				//기존에 있었다면 cookie 삭제 (빈값을 넣기)
				cookie = new Cookie("c_id","");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				cookie = new Cookie("c_id_val","");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			
		}//if(cnt>0) end
		
		if(cnt>0) {
			if(map.get("rurl") != null && !map.get("rurl").equals("")) {
				model.addAttribute("bbsno",map.get("bbsno"));
				model.addAttribute("nPage",map.get("nPage"));
				model.addAttribute("nowPage",map.get("nowPage"));
				model.addAttribute("col",map.get("col"));
				model.addAttribute("word",map.get("word"));
				
				return "redirect:"+map.get("rurl");
			}else {
				//home으로 redirect
				return "redirect:/";				
			}
			
		}else { //회원이 아닌경우.
			model.addAttribute("msg", "id또는 password를 잘못 입력했거나 <br> 회원이 아닙니다. 회원가입을 하세요.");
			return "/member/errorMsg";
		}
	}
	
	@GetMapping("/member/login")
	public String login(HttpServletRequest request) {
		/*----쿠키설정 내용시작----------------------------*/
		String c_id = "";     // ID 저장 여부를 저장하는 변수, Y
		String c_id_val = ""; // ID 값
		 
		Cookie[] cookies = request.getCookies(); 
		Cookie cookie=null; 
		 
		if (cookies != null){ 
		 for (int i = 0; i < cookies.length; i++) { 
		   cookie = cookies[i]; 
		 //cookies array에 cookie요소가 각각 cookie이름 & cookie값을 저장하고있다
		   if (cookie.getName().equals("c_id")){ 
		     c_id = cookie.getValue();     // Y 
		   }else if(cookie.getName().equals("c_id_val")){ 
		     c_id_val = cookie.getValue(); // user1... 
		   } 
		 } 
		} 
		/*----쿠키설정 내용 끝----------------------------*/
		request.setAttribute("c_id", c_id);
		request.setAttribute("c_id_val", c_id_val);
		return "/member/login";
	}
	
	@PostMapping("/member/create")
	public String create(MemberDTO dto, HttpServletRequest request) {
		String upDir = request.getRealPath("/storage");
		String fname = Utility.saveFileSpring(dto.getFnameMF(),upDir);
		
		int size = (int) dto.getFnameMF().getSize();
		if(size>0) {
			dto.setFname(fname);
		}
		else {
			dto.setFname("arrow.jpg");
		}
		if(mapper.create(dto) > 0) {
			return "redirect:/";
		}
		else {
			return "error";
		}
	}
	
	/*Controller에서 RestController의 경우와같이 data를 가져오기위해서는 
	 * 아래와 같이 @GetMapping(value="요청url") 와 @ResponseBody를 지정해야함.
	 * ()안에 produces=""는 한글이 데이터로 넘어오는 경우에만 한글처리를 위해 필요함.
	 * 
	 * 이 Controller method가 반환할 타입 지정:
	 * data 자체의 반환을 위해 @ResponseBody를 표기 + Map<>을 지정
	 */
	@GetMapping(value="/member/idcheck", produces="application/json;charset=utf-8")
	@ResponseBody
	public Map<String, String> idcheck(String id){
		int cnt = mapper.duplicatedId(id);
		System.out.println("cnt:"+cnt);
		Map<String, String> map = new HashMap<String, String>();
		if(cnt>0) {
			map.put("str", id+"는 중복되어서 사용할 수 없습니다.");
		}
		else {
			map.put("str", id+"는 중복아님, 사용 가능합니다.");	
		}
		return map;
	}
	
	
	@GetMapping(value="/member/emailcheck", produces="application/json;charset=utf-8")
	@ResponseBody
	public Map<String, String> emailcheck(String email){
		int cnt = mapper.duplicatedEmail(email);
		System.out.println("cnt:"+cnt);
		Map<String, String> map = new HashMap<String, String>();
		if(cnt>0) {
			map.put("str", email+"는 중복되어서 사용할 수 없습니다.");
		}
		else {
			map.put("str", email+"는 중복아님, 사용 가능합니다.");	
		}
		return map;
	}
	
	@GetMapping("/member/agree")
	public String agree() {
		//tiles 사용하여 반환되는 url과 agreement.jsp을 mapping
		return "/member/agree";
	}
	
	@PostMapping("/member/createForm")
	public String create() {
		return "/member/create";
	}
	
	
}
