package br.sharing.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class Interceptador extends HandlerInterceptorAdapter {

	private static String[] paginasAbertas = {"formLogin", "login", "home"};
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String URI = request.getRequestURI();
		return true;
//		for (String a : paginasAbertas)
//			if (URI.endsWith(a.toString()))
//				return true;
//		if(URI.endsWith("formLogin") || URI.endsWith("login")){
//			return true;
//		}
		
//		if (request.getSession().getAttribute("aluno_logado") != null) {
//			return true;
//		}
//		
//		response.sendRedirect("formLogin");
//		
//		return false;
	}

}