package com.douzone.jblog.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import com.douzone.jblog.service.UserService;

public class BlogInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);		
		String blogId = (String)pathVariables.get("id");
		
		List<String> userIdList = userService.findAllUser();
		
		// 유저 아이디가 없으면(블로그도 없음) jblog 메인으로 리다이렉트
		if(userIdList.indexOf(blogId) == -1) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		return true;
	}

}
