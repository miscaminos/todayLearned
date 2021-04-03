package com.study.contents;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.study.cate.CateService;
import com.study.cate.CateVO;
import com.study.categrp.CategrpService;
import com.study.categrp.CategrpVO;
import com.study.utility.Utility;

@Controller
public class ContentsCont {

	@Autowired
	@Qualifier("com.study.categrp.CategrpServiceImpl")
	private CategrpService gservice;

	@Autowired
	@Qualifier("com.study.cate.CateServiceImpl")
	private CateService cservice;

	@Autowired
	@Qualifier("com.study.contents.ContentsServiceImpl")
	private ContentsService service;

	public ContentsCont() {
		System.out.println("--> ContentsCont created.");
	}

	@RequestMapping(value = "/contents/create", method = RequestMethod.GET)
	public ModelAndView create(int cateno) {
		ModelAndView mav = new ModelAndView();

		CateVO cateVO = this.cservice.read(cateno);
		CategrpVO categrpVO = this.gservice.read(cateVO.getCategrpno());

		mav.addObject("cateVO", cateVO);
		mav.addObject("categrpVO", categrpVO);

		mav.setViewName("/contents/create");

		return mav;
	}

	@RequestMapping(value = "/contents/create", method = RequestMethod.POST)
	public ModelAndView create(HttpServletRequest request, ContentsVO contentsVO) throws IOException {
		contentsVO.setIp(request.getRemoteAddr());

		ModelAndView mav = new ModelAndView();
		String file1 = ""; // main image
		String thumb1 = ""; // preview image
		
		//System.getProperty로 root경로를 얻을수있음. 
		//BUT 이방식으로는 새로운 파일 생성시마다 folder refresh가 필요함.
		//String user_dir = System.getProperty("user.dir");
		//String upDir = user_dir + "/src/main/resources/static/contents/storage/main_images/";
		
		//아래 방식으로 대신 upDir 지정
		String upDir = new ClassPathResource("/static/contents/storage/main_images").getFile().getAbsolutePath();
		MultipartFile mf = contentsVO.getFile1MF();

		int size1 = (int)mf.getSize();
		if (size1 > 0) {
			file1 = Utility.saveFileSpring(mf, upDir);

			if (Utility.isImage(file1)) {

				thumb1 = Utility.preview(upDir, file1, 200, 150);
			}

		}

		contentsVO.setFile1(file1);
		contentsVO.setThumb1(thumb1);
		contentsVO.setSize1(size1);
		int cnt = this.service.create(contentsVO);

		System.out.println("--> contentsno: " + contentsVO.getContentsno());
		mav.addObject("contentsno", contentsVO.getContentsno());
		if (cnt == 1) {
			cservice.increaseCnt(contentsVO.getCateno());
		}
		mav.addObject("cnt", cnt);

		mav.addObject("cateno", contentsVO.getCateno());
		mav.addObject("url", "create_msg");
		mav.setViewName("redirect:/contents/msg");

		return mav;
	}

	@RequestMapping(value = "/contents/list", method = RequestMethod.GET)
	public ModelAndView list_by_cateno_search_paging_join(
			@RequestParam(value = "cateno", defaultValue = "1") int cateno,
			@RequestParam(value = "word", defaultValue = "") String word,
			@RequestParam(value = "nowPage", defaultValue = "1") int nowPage) {
		System.out.println("--> nowPage: " + nowPage);

		ModelAndView mav = new ModelAndView();

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cateno", cateno); // #{cateno}
		map.put("word", word); // #{word}
		map.put("nowPage", nowPage);

		List<Contents_UsersVO> list = service.list_by_cateno_search_paging_join(map);
		mav.addObject("list", list);

		int search_count = service.search_count(map);
		mav.addObject("search_count", search_count);

		CateVO cateVO = cservice.read(cateno);
		mav.addObject("cateVO", cateVO);

		CategrpVO categrpVO = gservice.read(cateVO.getCategrpno());
		mav.addObject("categrpVO", categrpVO);

		String paging = Utility.pagingBox("list", cateno, search_count, nowPage, word);
		mav.addObject("paging", paging);

		mav.addObject("nowPage", nowPage);

		// /contents/list_by_cateno_search_paging_join.jsp
		mav.setViewName("/contents/list_by_cateno_search_paging_join");

		return mav;
	}

	@RequestMapping(value = "/contents/list_by_cateno_grid", method = RequestMethod.GET)
	public ModelAndView list_by_cateno_grid1(int cateno) {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("/contents/list_by_cateno_grid");

		CateVO cateVO = this.cservice.read(cateno);
		mav.addObject("cateVO", cateVO);

		CategrpVO categrpVO = this.gservice.read(cateVO.getCategrpno());
		mav.addObject("categrpVO", categrpVO);

		List<ContentsVO> list = this.service.list_by_cateno(cateno);
		mav.addObject("list", list);

		return mav; // forward
	}

	@RequestMapping(value = "/contents/read", method = RequestMethod.GET)
	public ModelAndView read(int contentsno) {
		ModelAndView mav = new ModelAndView();

		ContentsVO contentsVO = this.service.read(contentsno);
		mav.addObject("contentsVO", contentsVO); // request.setAttribute("contentsVO", contentsVO);

		CateVO cateVO = this.cservice.read(contentsVO.getCateno());
		mav.addObject("cateVO", cateVO);

		CategrpVO categrpVO = this.gservice.read(cateVO.getCategrpno());
		mav.addObject("categrpVO", categrpVO);

		mav.setViewName("/contents/read_img"); // /webapp/WEB-INF/views/contents/read_img.jsp

		return mav;
	}

	@RequestMapping(value = "/contents/update", method = RequestMethod.GET)
	public ModelAndView update(int contentsno) {
		ModelAndView mav = new ModelAndView();

		ContentsVO contentsVO = this.service.read_update(contentsno);
		mav.addObject("contentsVO", contentsVO); // request.setAttribute("contentsVO", contentsVO);

		mav.setViewName("/contents/update"); // /contents/update.jsp

		return mav;
	}

	@RequestMapping(value = "/contents/update", method = RequestMethod.POST)
	public ModelAndView update(ContentsVO contentsVO) {
		ModelAndView mav = new ModelAndView();

		CateVO cateVO = this.cservice.read(contentsVO.getCateno());
		// mav.addObject("cateVO", cateVO);
		mav.addObject("cate_name", cateVO.getName());
		mav.addObject("cateno", cateVO.getCateno());

		CategrpVO categrpVO = this.gservice.read(cateVO.getCategrpno());
		// mav.addObject("categrpVO", categrpVO);
		mav.addObject("categrp_name", categrpVO.getName());

		mav.addObject("contentsno", contentsVO.getContentsno());

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("contentsno", contentsVO.getContentsno());
		hashMap.put("passwd", contentsVO.getPasswd());

		int passwd_cnt = 0;
		int cnt = 0;

		passwd_cnt = this.service.passwd_check(hashMap);

		if (passwd_cnt == 1) {
			cnt = this.service.update(contentsVO);
		}

		mav.addObject("cnt", cnt);
		mav.addObject("passwd_cnt", passwd_cnt);

		mav.addObject("url", "update_msg"); // /contents/update_msg.jsp

		mav.setViewName("redirect:/contents/msg");

		return mav;
	}

	@RequestMapping(value = "/contents/delete", method = RequestMethod.GET)
	public ModelAndView delete(int contentsno) {
		ModelAndView mav = new ModelAndView();

		ContentsVO contentsVO = this.service.read(contentsno);
		mav.addObject("contentsVO", contentsVO);
		mav.setViewName("/contents/delete"); // contents/delete.jsp

		return mav;
	}

	@RequestMapping(value = "/contents/delete", method = RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request, int cateno, int contentsno, String passwd,
			@RequestParam(value = "word", defaultValue = "") String word,
			@RequestParam(value = "nowPage", defaultValue = "1") int nowPage) throws IOException {
		ModelAndView mav = new ModelAndView();

		ContentsVO contentsVO = this.service.read(contentsno);
		String title = contentsVO.getTitle();
		mav.addObject("title", title);

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("contentsno", contentsno);
		hashMap.put("passwd", passwd);

		int passwd_cnt = 0;
		int cnt = 0;

		passwd_cnt = this.service.passwd_check(hashMap);
		boolean sw = false;

		if (passwd_cnt == 1) {
			cnt = this.service.delete(contentsno);
			if (cnt == 1) {
				cservice.decreaseCnt(cateno);

				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("cateno", cateno);
				map.put("word", word);

				if (service.search_count(map) % Contents.RECORD_PER_PAGE == 0) {
					nowPage = nowPage - 1;
					if (nowPage < 1) {
						nowPage = 1; //
					}
				}
				// -------------------------------------------------------------------------------------
			}

			//String user_dir = System.getProperty("user.dir");
			//System.out.println("--> User dir: " + user_dir);
			//String upDir = user_dir + "/src/main/resources/static/contents/storage/main_images/"; // ???? ???

			String upDir = new ClassPathResource("/static/contents/storage/main_images").getFile().getAbsolutePath();
			MultipartFile mf = contentsVO.getFile1MF();
			
			Utility.deleteFile(upDir, contentsVO.getFile1());
			Utility.deleteFile(upDir, contentsVO.getThumb1());

		}

		mav.addObject("cnt", cnt);
		mav.addObject("passwd_cnt", passwd_cnt);
		mav.addObject("nowPage", nowPage);
		// System.out.println("--> ContentsCont.java nowPage: " + nowPage);

		mav.addObject("cateno", contentsVO.getCateno());
		mav.addObject("url", "delete_msg");

		// mav.setViewName("/contents/delete_msg"); // webapp/contents/delete_msg.jsp
		mav.setViewName("redirect:/contents/msg");

		return mav;
	}

	/**
	 * 이미지 등록 폼
	 * 
	 * @param contentsno
	 * @return
	 */
	@RequestMapping(value = "/contents/img_create", method = RequestMethod.GET)
	public ModelAndView img_create(int contentsno) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/contents/img_create"); // /webapp/contents/img_create.jsp

		ContentsVO contentsVO = this.service.read(contentsno);
		mav.addObject("contentsVO", contentsVO);

		CateVO cateVO = this.cservice.read(contentsVO.getCateno());
		mav.addObject("cateVO", cateVO);

		CategrpVO categrpVO = this.gservice.read(cateVO.getCategrpno());
		mav.addObject("categrpVO", categrpVO);

		return mav; // forward
	}

	/**
	 * 이미지 등로 처리
	 * 
	 * @param request
	 * @param contentsVO
	 * @param nowPage
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/contents/img_create", method = RequestMethod.POST)
	public ModelAndView img_create(HttpServletRequest request, ContentsVO contentsVO,
			@RequestParam(value = "nowPage", defaultValue = "1") int nowPage) throws IOException {
		ModelAndView mav = new ModelAndView();

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("contentsno", contentsVO.getContentsno());
		hashMap.put("passwd", contentsVO.getPasswd());

		int passwd_cnt = 0; // 패스워드 일치 레코드 갯수
		int cnt = 0; // 수정된 레코드 갯수

		passwd_cnt = this.service.passwd_check(hashMap);// 패스워드가 일치할 경우 파일 업로드

		if (passwd_cnt == 1) {
			// 파일 전송 코드 시작
			String file1 = ""; // main image
			String thumb1 = ""; // preview image

			//String upDir = System.getProperty("user.dir") + "/src/main/resources/static/contents/storage/main_images/";
			String upDir = new ClassPathResource("/static/contents/storage/main_images").getFile().getAbsolutePath();
			MultipartFile mf = contentsVO.getFile1MF();
			int size1 = (int)mf.getSize();
			if (size1 > 0) {

				file1 = Utility.saveFileSpring(mf, upDir);

				if (Utility.isImage(file1)) { // 이미지 인지 검사
					// thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
					thumb1 = Utility.preview(upDir, file1, 200, 150);
				}
			}

			contentsVO.setFile1(file1);
			contentsVO.setThumb1(thumb1);
			contentsVO.setSize1(size1);
			// 파일 전송 코드 종료
			mav.addObject("nowPage", nowPage);
			mav.addObject("contentsno", contentsVO.getContentsno());

			mav.setViewName("redirect:/contents/read");

			cnt = this.service.img_create(contentsVO);

		} else {
			mav.addObject("url", "update_msg"); // webapp/contents/update_msg.jsp
			mav.setViewName("redirect:/contents/msg");

		}

		mav.addObject("cnt", cnt);
		mav.addObject("passwd_cnt", passwd_cnt);

		return mav;
	}

	/**
	 * 이미지 삭제 / 수정 폼
	 * 
	 * @param contentsno
	 * @return
	 */
	@RequestMapping(value = "/contents/img_update", method = RequestMethod.GET)
	public ModelAndView img_update(int contentsno) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/contents/img_update"); // //contents/img_update.jsp

		ContentsVO contentsVO = this.service.read(contentsno);
		mav.addObject("contentsVO", contentsVO);

		CateVO cateVO = this.cservice.read(contentsVO.getCateno());
		mav.addObject("cateVO", cateVO);

		CategrpVO categrpVO = this.gservice.read(cateVO.getCategrpno());
		mav.addObject("categrpVO", categrpVO);

		return mav; // forward
	}

	/**
	 * 이미지 수정 처리
	 * 
	 * @param request
	 * @param contentsVO
	 * @param nowPage
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/contents/img_update", method = RequestMethod.POST)
	public ModelAndView img_update(HttpServletRequest request, ContentsVO contentsVO,
			@RequestParam(value = "nowPage", defaultValue = "1") int nowPage) throws IOException {
		ModelAndView mav = new ModelAndView();

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("contentsno", contentsVO.getContentsno());
		hashMap.put("passwd", contentsVO.getPasswd());

		int passwd_cnt = 0;
		int cnt = 0;

		passwd_cnt = this.service.passwd_check(hashMap);

		if (passwd_cnt == 1) {
			// 파일 삭제 코드 시작
			ContentsVO vo = service.read(contentsVO.getContentsno());

			String file1 = vo.getFile1().trim();
			String thumb1 = vo.getThumb1().trim();
			int size1 = 0;
			boolean sw = false;

			//String upDir = System.getProperty("user.dir") + "/src/main/resources/static/contents/storage/main_images/";
			String upDir = new ClassPathResource("/static/contents/storage/main_images").getFile().getAbsolutePath();
						
			Utility.deleteFile(upDir, vo.getFile1());
			Utility.deleteFile(upDir, vo.getThumb1());
			// 파일 삭제 종료 시작

			// 파일 전송 코드 시작
			MultipartFile mf = contentsVO.getFile1MF();

			size1 = (int)mf.getSize(); // 파일 크기
			if (size1 > 0) { // 파일 크기 체크
				// 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
				file1 = Utility.saveFileSpring(mf, upDir);
				if (Utility.isImage(file1)) { // 이미지인지 검사
					// thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
					thumb1 = Utility.preview(upDir, file1, 200, 150);
				}
			}

			contentsVO.setFile1(file1);
			contentsVO.setThumb1(thumb1);
			contentsVO.setSize1(size1);
			// 파일 전송 코드 종료

			mav.addObject("nowPage", nowPage);
			mav.addObject("contentsno", contentsVO.getContentsno());
			mav.setViewName("redirect:/contents/read");

			cnt = this.service.img_create(contentsVO);
			// mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)

		} else {
			mav.addObject("url", "update_msg"); // webapp/contents/update_msg.jsp
			mav.setViewName("redirect:/contents/msg");

		}

		mav.addObject("cnt", cnt);
		mav.addObject("passwd_cnt", passwd_cnt);

		return mav;
	}

	/**
	 * 이미지 삭제 처리
	 * 
	 * @param request
	 * @param contentsno
	 * @param cateno
	 * @param passwd
	 * @param nowPage
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/contents/img_delete", method = RequestMethod.POST)
	public ModelAndView img_delete(HttpServletRequest request, int contentsno, int cateno, String passwd,
			@RequestParam(value = "nowPage", defaultValue = "1") int nowPage) throws IOException {
		ModelAndView mav = new ModelAndView();

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("contentsno", contentsno);
		hashMap.put("passwd", passwd);

		int passwd_cnt = 0;
		int cnt = 0;

		passwd_cnt = this.service.passwd_check(hashMap);

		if (passwd_cnt == 1) {

			ContentsVO contentsVO = service.read(contentsno);
			// System.out.println("file1: " + contentsVO.getFile1());

			String file1 = contentsVO.getFile1().trim();
			String thumb1 = contentsVO.getThumb1().trim();
			long size1 = contentsVO.getSize1();
			boolean sw = false;

			//String upDir = System.getProperty("user.dir") + "/src/main/resources/static/contents/storage/main_images/";
			String upDir = new ClassPathResource("/static/contents/storage/main_images").getFile().getAbsolutePath();
			Utility.deleteFile(upDir, contentsVO.getFile1());
			Utility.deleteFile(upDir, contentsVO.getThumb1());
			// System.out.println("sw: " + sw);

			file1 = "";
			thumb1 = "";
			size1 = 0;

			contentsVO.setFile1(file1);
			contentsVO.setThumb1(thumb1);
			contentsVO.setSize1((int)size1);

			mav.addObject("nowPage", nowPage);
			mav.addObject("contentsno", contentsno);
			mav.setViewName("redirect:/contents/read");

			cnt = this.service.img_delete(contentsVO);
			// mav.addObject("cnt", cnt); // request.setAttribute("cnt", cnt)

		} else {
			mav.addObject("url", "update_msg");
			mav.setViewName("redirect:/contents/msg");

		}

		mav.addObject("cnt", cnt);
		mav.addObject("passwd_cnt", passwd_cnt);

		return mav;
	}

	/**
	 * 새로고침 방지하는 메시지 출력
	 * 
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/contents/msg", method = RequestMethod.GET)
	public ModelAndView msg(String url) {
		ModelAndView mav = new ModelAndView();

		mav.setViewName("/contents/" + url); // forward

		return mav; // forward
	}

	// http://localhost:8000/contents/passwd?contentsno=28&passwd=123
	/**
	 * 글의 패스워드 체크, JSON 출력
	 * 
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "/contents/passwd.", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String passwd(int contentsno, String passwd) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("contentsno", contentsno);
		hashMap.put("passwd", passwd);

		int cnt = this.service.passwd_check(hashMap);

		JSONObject json = new JSONObject();
		json.put("cnt", cnt);

		return json.toString();
	}

	@GetMapping("/contents/fileDown")
	public void fileDown(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ServletContext ctx = request.getSession().getServletContext();

		// 저장 폴더를 절대 경로로 변환
		// String dir = ctx.getRealPath(request.getParameter("dir"));

		String user_dir = System.getProperty("user.dir");
		System.out.println("--> User dir: " + user_dir);
		String dir = user_dir + "/src/main/resources/static/contents/storage/main_images/";

		// 파일명 받기
		String filename = request.getParameter("filename");
		System.out.println("filename:" + filename);
		System.out.println("dir:" + dir);

		byte[] files = FileUtils.readFileToByteArray(new File(dir, filename));

		response.setHeader("Content-disposition",
				"attachment; fileName=\"" + URLEncoder.encode(filename, "UTF-8") + "\";");
		// Content-Transfer-Encoding : 전송 데이타의 body를 인코딩한 방법을 표시함.
		response.setHeader("Content-Transfer-Encoding", "binary");
		/**
		 * Content-Disposition가 attachment와 함게 설정되었다면 'Save As'로 파일을 제안하는지 여부에 따라 브라우저가
		 * 실행한다.
		 */
		response.setContentType("application/octet-stream");
		response.setContentLength(files.length);
		response.getOutputStream().write(files);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

}
