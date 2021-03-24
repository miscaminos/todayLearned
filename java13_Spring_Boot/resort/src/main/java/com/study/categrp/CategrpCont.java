package com.study.categrp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategrpCont {

	@Autowired
	@Qualifier("com.study.categrp.CategrpServiceImpl")
	private CategrpService service;

	public CategrpCont() {
		System.out.println("------>CategrpController created.");
	}

	@RequestMapping(value = "categrp/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView();

		List<CategrpVO> list = service.list_seqno_asc();
		mav.addObject("list", list);
		mav.setViewName("/categrp/list");
		// tiles.xml에서 definition attribute로 viewName과
		// "/webapp/categrp/list.jsp"파일 경로가 mapping됨.
		return mav;
	}

	// 현재 구현된 view page에서는 /create GET방식 호출 미사용중.
	@RequestMapping(value = "categrp/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/categrp/create");
		return mav;
	}

	@RequestMapping(value = "/categrp/create", method = RequestMethod.POST)
	public ModelAndView create(CategrpVO vo) {

		ModelAndView mav = new ModelAndView();
		int cnt = service.create(vo);
		mav.addObject("cnt", cnt);
		mav.setViewName("/categrp/create_msg");

		return mav;
	}

	@RequestMapping(value = "categrp/read_update", method = RequestMethod.GET)
	public ModelAndView update(int categrpno) {
		ModelAndView mav = new ModelAndView();

		CategrpVO vo = service.read(categrpno);
		mav.addObject("vo", vo);
		mav.setViewName("/categrp/read_update");
		return mav;
	}

	@RequestMapping(value = "categrp/update", method = RequestMethod.POST)
	public ModelAndView update(CategrpVO vo) {
		ModelAndView mav = new ModelAndView();
		int cnt = service.update(vo);
		mav.addObject("cnt", cnt);
		mav.setViewName("/categrp/update_msg");
		return mav;

	}

	@RequestMapping(value = "/categrp/update_visible", method = RequestMethod.GET)
	public ModelAndView update_visible(int categrpno, String visible) {
		ModelAndView mav = new ModelAndView();

		Map map = new HashMap();
		map.put("categrpno", categrpno);
		map.put("visible", visible);

		int cnt = this.service.update_visible(map);

		mav.setViewName("redirect:/categrp/list");

		return mav;
	}

	////////////////////////////////////////////////////////

	@RequestMapping(value = "/categrp/read_delete", method = RequestMethod.GET)
	public ModelAndView read_delete(int categrpno) {
		ModelAndView mav = new ModelAndView();

		CategrpVO categrpVO = this.service.read(categrpno);
		mav.addObject("categrpVO", categrpVO);

		List<CategrpVO> list = this.service.list_seqno_asc();
		mav.addObject("list", list);

		mav.setViewName("/categrp/read_delete");
		return mav;
	}

	@RequestMapping(value = "/categrp/delete", method = RequestMethod.POST)
	public ModelAndView delete(int categrpno) {
		ModelAndView mav = new ModelAndView();

		CategrpVO categrpVO = this.service.read(categrpno);
		mav.addObject("categrpVO", categrpVO);

		int cnt = this.service.delete(categrpno);
		mav.addObject("cnt", cnt);

		mav.setViewName("/categrp/delete_msg");

		return mav;
	}

	@RequestMapping(value = "/categrp/update_seqno_up", method = RequestMethod.GET)
	public ModelAndView update_seqno_up(int categrpno) {
		ModelAndView mav = new ModelAndView();

		CategrpVO categrpVO = this.service.read(categrpno);
		mav.addObject("categrpVO", categrpVO);

		int cnt = this.service.update_seqno_up(categrpno);
		mav.addObject("cnt", cnt);

		mav.setViewName("/categrp/update_seqno_up_msg");
		return mav;
	}

	@RequestMapping(value = "/categrp/update_seqno_down", method = RequestMethod.GET)
	public ModelAndView update_seqno_down(int categrpno) {
		ModelAndView mav = new ModelAndView();

		CategrpVO categrpVO = this.service.read(categrpno);
		mav.addObject("categrpVO", categrpVO);

		int cnt = this.service.update_seqno_down(categrpno);
		mav.addObject("cnt", cnt);

		mav.setViewName("/categrp/update_seqno_down_msg");
		return mav;
	}

}
