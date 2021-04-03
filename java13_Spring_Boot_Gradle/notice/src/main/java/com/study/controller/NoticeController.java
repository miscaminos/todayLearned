package com.study.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.model.NoticeDTO;
import com.study.model.NoticeService;
//import com.study.model.NoticeServiceImpl;
import com.study.notice.Utility;

@Controller
public class NoticeController {
	
//	@Autowired
//	private NoticeServiceImpl service; 
	
	//NoticeService(interface)를 사용하려면, 구현 클래스 fullname을 Qualifier로 명시해야한다
	//=>NoticeService interface를 구현한 NoticeServiceImpl 클래스를 대신 사용하겠다는 선언.
	@Autowired
	@Qualifier("com.study.model.NoticeServiceImpl")
	private NoticeService service;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		//검색관련---
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		
		if(col.equals("total")) {
			word="";
		}
		
		//페이지관련---
		int nowPage =1; // 현재 보고있는 페이지
		if (request.getParameter("nowPage") != null) {
		      nowPage = Integer.parseInt(request.getParameter("nowPage"));
		    }
		int recordPerPage = 3;// 한페이지당 보여줄 레코드갯수
		
		// MariaDB에서 가져올 순서 앞부분 0부터 시작
		//Oracle과는 다르게, MariaDB는 앞부분 번호 & 앞부분 번호부터 몇개를 지정해서 가져온다
		//(Oracle을 사용했을때에는 어디부터 어디까지 가져올지를 지정했음)
		int sno = ((nowPage - 1) * recordPerPage) ;
	
	    Map map = new HashMap();
	    map.put("col", col);
	    map.put("word", word);
	    map.put("sno", sno);
	    map.put("cnt", recordPerPage);
	 
	    int total = service.total(map);
	 
	    List<NoticeDTO> list = service.list(map);
	 
	    String paging = Utility.paging(total, nowPage, recordPerPage, col, word);
	 
	    // request에 Model사용 결과 담는다
	    request.setAttribute("list", list);
	    request.setAttribute("nowPage", nowPage);
	    request.setAttribute("col", col);
	    request.setAttribute("word", word);
	    request.setAttribute("paging", paging);
	 
	    // view페이지 리턴
		return "/list";
	}
	
	@PostMapping("/create")
	public String create(NoticeDTO dto) {
		if (service.create(dto)==1) {
			return "redirect:/list";
		}else {
			return "/error";			
		}
	}
	
	@GetMapping("/create")
	public String create() {
		return "/create";
	}
	
	@GetMapping("/")
	public String home(Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = 
				DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
	
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime",formattedDate);
		return "/home";
	}
}
