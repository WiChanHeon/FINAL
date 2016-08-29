package kr.spring.member.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogonCheckInterceptor extends HandlerInterceptorAdapter {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)throws Exception{
		
		if(log.isDebugEnabled()){
			log.debug("=로그인 체크 인터셉터 진입=");
		}
		
		//session 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("userId")==null){
			response.sendRedirect(request.getContextPath()+"/member/login.do");
			return false;
		}
		return true;
	}
}
