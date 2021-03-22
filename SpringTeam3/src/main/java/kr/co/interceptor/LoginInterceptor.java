package kr.co.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		// 로그인 되어있다면 로그아웃 시킴
		Object login= session.getAttribute("login");
		 if(login != null) {
			 session.removeAttribute("login"); //로그아웃방법1
		 }
		 
		return true;	
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 세션 작업
		HttpSession session = request.getSession();
		Object login = modelAndView.getModel().get("login");
		if(login != null) {
			session.setAttribute("login", login);
			
			String referer = (String) session.getAttribute("referer");// 이전페이지
			response.sendRedirect(referer !=null ? (referer.equals("http://localhost:8089/member/login") ? "/": referer ) : "/");
			
		}else {
			response.sendRedirect("/member/login");
		}
		
		
		
		
	
	}

	
	
}
