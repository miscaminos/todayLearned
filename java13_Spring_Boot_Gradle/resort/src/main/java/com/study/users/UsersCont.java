package com.study.users;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsersCont {
	
	@Autowired
	@Qualifier("com.study.users.UsersServiceImpl")
	private UsersService service;
	
	public UsersCont() {
		System.out.println("---> UsersCont created");
	}
	
   @ResponseBody
   @RequestMapping(value = "/users/checkID", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
   public String checkID(String id) {
     int cnt = this.service.checkID(id);

     JSONObject json = new JSONObject();
     json.put("cnt", cnt);

     return json.toString();
   }

  @RequestMapping(value = "/users/create", method = RequestMethod.GET)
  public ModelAndView create() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/users/create");

    return mav;
  }

  @RequestMapping(value = "/users/create", method = RequestMethod.POST)
  public ModelAndView create(UsersVO usersVO) {
    ModelAndView mav = new ModelAndView();

    int cnt = service.create(usersVO);
    mav.addObject("cnt", cnt);
    mav.addObject("url", "create_msg");

    mav.setViewName("redirect:/users/msg");

    return mav;
  }

  @RequestMapping(value = "/users/msg", method = RequestMethod.GET)
  public ModelAndView msg(String url) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/users/" + url); // forward

    return mav; // forward
  }

  @RequestMapping(value = "/users/list", method = RequestMethod.GET)
  public ModelAndView list(HttpSession session) {
    ModelAndView mav = new ModelAndView();

    List<UsersVO> list = service.list();
    mav.addObject("list", list);

    mav.setViewName("/users/list");

    return mav;
  }

  @RequestMapping(value = "/users/read", method = RequestMethod.GET)
  public ModelAndView read(int usersno) {
    ModelAndView mav = new ModelAndView();

    UsersVO usersVO = this.service.read(usersno);
    mav.addObject("usersVO", usersVO);
    mav.setViewName("/users/read");

    return mav; // forward
  }

  @RequestMapping(value = "/users/update", method = RequestMethod.POST)
  public ModelAndView update(UsersVO usersVO) {
    ModelAndView mav = new ModelAndView();

    int cnt = service.update(usersVO);
    mav.addObject("cnt", cnt);
    mav.addObject("usersno", usersVO.getUsersno());
    mav.addObject("url", "update_msg");

    mav.setViewName("redirect:/users/msg");

    return mav;
  }

  @RequestMapping(value = "/users/delete", method = RequestMethod.GET)
  public ModelAndView delete(int usersno) {
    ModelAndView mav = new ModelAndView();

    UsersVO usersVO = this.service.read(usersno);
    mav.addObject("usersVO", usersVO);
    mav.setViewName("/users/delete");

    return mav;
  }

  @RequestMapping(value = "/users/delete", method = RequestMethod.POST)
  public ModelAndView delete_proc(int usersno) {
    ModelAndView mav = new ModelAndView();

    UsersVO usersVO = this.service.read(usersno);

    int cnt = service.delete(usersno);
    mav.addObject("cnt", cnt);
    mav.addObject("name", usersVO.getName());
    mav.addObject("url", "delete_msg");

    mav.setViewName("redirect:/users/msg");

    return mav;
  }

  @RequestMapping(value = "/users/passwd_update", method = RequestMethod.GET)
  public ModelAndView passwd_update(int usersno) {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("/users/passwd_update");

    return mav;
  }

  @RequestMapping(value = "/users/passwd_update", method = RequestMethod.POST)
  public ModelAndView passwd_update(int usersno, String current_passwd, String new_passwd) {
    ModelAndView mav = new ModelAndView();

    HashMap<Object, Object> map = new HashMap<Object, Object>();
    map.put("usersno", usersno);
    map.put("passwd", current_passwd);

    int cnt = service.passwd_check(map);
    int update_cnt = 0;

    if (cnt == 1) {
      map.put("passwd", new_passwd);
      update_cnt = service.passwd_update(map);
      mav.addObject("update_cnt", update_cnt);
    }

    mav.addObject("cnt", cnt);
    mav.addObject("url", "passwd_update_msg");

    mav.setViewName("redirect:/users/msg");

    return mav;
  }

  @RequestMapping(value = "/users/login", method = RequestMethod.GET)
  public ModelAndView login_cookie(HttpServletRequest request) {
    ModelAndView mav = new ModelAndView();

    Cookie[] cookies = request.getCookies();
    Cookie cookie = null;

    String ck_id = ""; // id
    String ck_id_save = "";
    String ck_passwd = "";
    String ck_passwd_save = "";

    if (cookies != null) {
      for (int i = 0; i < cookies.length; i++) {
        cookie = cookies[i];

        if (cookie.getName().equals("ck_id")) {
          ck_id = cookie.getValue();
        } else if (cookie.getName().equals("ck_id_save")) {
          ck_id_save = cookie.getValue(); // Y, N
        } else if (cookie.getName().equals("ck_passwd")) {
          ck_passwd = cookie.getValue(); // 1234
        } else if (cookie.getName().equals("ck_passwd_save")) {
          ck_passwd_save = cookie.getValue(); // Y, N
        }
      }
    }

    mav.addObject("ck_id", ck_id);
    mav.addObject("ck_id_save", ck_id_save);
    mav.addObject("ck_passwd", ck_passwd);
    mav.addObject("ck_passwd_save", ck_passwd_save);

    mav.setViewName("/users/login_ck_form");
    return mav;
  }

  @RequestMapping(value = "/users/login", method = RequestMethod.POST)
  public ModelAndView login_cookie_proc(HttpServletRequest request, HttpServletResponse response, HttpSession session,
      String id, String passwd, @RequestParam(value = "id_save", defaultValue = "") String id_save,
      @RequestParam(value = "passwd_save", defaultValue = "") String passwd_save) {
    ModelAndView mav = new ModelAndView();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("id", id);
    map.put("passwd", passwd);

    int count = service.login(map);
    if (count == 1) {
      UsersVO usersVO = service.readById(id);
      session.setAttribute("usersno", usersVO.getUsersno());
      session.setAttribute("id", id);
      session.setAttribute("name", usersVO.getName());

      if (id_save.equals("Y")) {
        Cookie ck_id = new Cookie("ck_id", id);
        ck_id.setMaxAge(60 * 60 * 72 * 10); // 30 day
        response.addCookie(ck_id);
      } else {
        Cookie ck_id = new Cookie("ck_id", "");
        ck_id.setMaxAge(0);
        response.addCookie(ck_id);
      }

      Cookie ck_id_save = new Cookie("ck_id_save", id_save);
      ck_id_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
      response.addCookie(ck_id_save);
      // -------------------------------------------------------------------
      // Password
      // -------------------------------------------------------------------
      if (passwd_save.equals("Y")) {
        Cookie ck_passwd = new Cookie("ck_passwd", passwd);
        ck_passwd.setMaxAge(60 * 60 * 72 * 10); // 30 day
        response.addCookie(ck_passwd);
      } else { // N
        Cookie ck_passwd = new Cookie("ck_passwd", "");
        ck_passwd.setMaxAge(0);
        response.addCookie(ck_passwd);
      }

      Cookie ck_passwd_save = new Cookie("ck_passwd_save", passwd_save);
      ck_passwd_save.setMaxAge(60 * 60 * 72 * 10); // 30 day
      response.addCookie(ck_passwd_save);
      // -------------------------------------------------------------------

      mav.setViewName("redirect:/");
    } else {
      mav.addObject("url", "login_fail_msg");
      mav.setViewName("redirect:/users/msg");
    }

    return mav;
  }

  @RequestMapping(value = "/users/logout", method = RequestMethod.GET)
  public ModelAndView logout(HttpSession session) {
    ModelAndView mav = new ModelAndView();
    session.invalidate(); //

    mav.addObject("url", "logout_msg");
    mav.setViewName("redirect:/users/msg");

    return mav;
  }

  /**
   * Session test http://localhost:8000/users/session
   * 
   * @param session
   * @return
   */
  @RequestMapping(value = "/users/session", method = RequestMethod.GET)
  public ModelAndView session(HttpSession session) {
    ModelAndView mav = new ModelAndView();

    mav.addObject("url", "session");
    mav.setViewName("redirect:/users/msg");

    return mav;
  }

}

