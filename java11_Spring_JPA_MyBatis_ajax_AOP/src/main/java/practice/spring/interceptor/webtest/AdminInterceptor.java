package spring.interceptor.webtest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import spring.utility.webtest.Utility;

public class AdminInterceptor extends HandlerInterceptorAdapter {
	
	/* 요청 주소 결과 생성전에 실행:
	 * url 요청을 처리하기 전에 dispatcher servlet이 preHandle을 먼저 호출한다.
	 * Controller 처리 완료 전
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(true); //true:새로운 session, false:이미 만들어져있는 session
		
		System.out.println("preHandle executed");
		String grade = Utility.checkNull((String)session.getAttribute("grade"));
		
		if(grade.equals("A")) {
			return true; //요청 페이지로 계속 진행(admin/list)
		}else {
			//로그인 페이지로 이동(admin/list 요청처리 대신에 admin으로 로그인이 필요하다!)
			response.sendRedirect(request.getContextPath()+"/member/login");
			return false;
		}
	}
	
	/* 요청 처리 완료후 호출
	 * Controll가 처리를 다 완료후
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle executed");
		modelAndView.addObject("admin", "관리자관련기능을 출력");
		
	}
	/* jsp등 view페이지 출력전에 작동된다.
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion executed");
	}

}
