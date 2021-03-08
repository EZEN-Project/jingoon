package kr.co.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 로그인 인증
public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		Object login = session.getAttribute("login");
		
		if(login == null) {
			
			// 쿼리스트링과 요청 uri를 session에 바인딩
			String uri = request.getRequestURI();
			String query = request.getQueryString();
			if(query == null || query.equalsIgnoreCase("null")) {
				query = "";
			}else {
				query = "?" + query;
			}
			
			if(request.getMethod().equalsIgnoreCase("GET")) { // GET방식일 때만 세션에 uri와 쿼리 저장
				session.setAttribute("dest", uri+query);
			}
			// 로그인 안돼있을 때 -> 로그인 해라
			response.sendRedirect("/member/login");
			return false;
		}
		return true;
	}
}
