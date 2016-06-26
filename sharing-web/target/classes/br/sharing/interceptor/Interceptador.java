package br.sharing.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class Interceptador extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String URI = request.getRequestURI();
		
		if(URI.endsWith("formLogin") || URI.endsWith("login")){
			return true;
		}
		
		if (request.getSession().getAttribute("aluno_logado") != null) {
			return true;
		}
		
		response.sendRedirect("loginFormulario");
		
		return false;
	}

}