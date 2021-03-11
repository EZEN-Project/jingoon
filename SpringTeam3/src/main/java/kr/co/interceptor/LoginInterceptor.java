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
		System.out.println(":::::::::preHandle::::::::");
		
		HttpSession session = request.getSession();
		
		// 로그인 되어있다면 로그아웃 시킴
		Object login= session.getAttribute("login");
		 if(login != null) {
			 session.removeAttribute("login"); //로그아웃방법1
			 //session.invalidate(); 로그아웃방법2
			 //session.setAttribute("login", null); 로그아웃된거랑 같으나 이러면 안된다
		 }
		
		
		return true;	
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println(":::::::::postHandle::::::::");
		// 세션 작업
		HttpSession session = request.getSession();
		Object login = modelAndView.getModel().get("login");
		
		if(login != null) {
			session.setAttribute("login", login);
			
			String dest = (String) session.getAttribute("dest");
			response.sendRedirect(dest != null ? dest : "/");
			
			//response.sendRedirect("/");
		}else {
			response.sendRedirect("/member/login");
		}
		
		
		
		
		
	
	}

	
	
}
