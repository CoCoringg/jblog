package com.douzone.jblog.interceptor;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;

public class BlogAdminInterceptor implements HandlerInterceptor {
	
	@Autowired
	private BlogService blogService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);		
		String blogId = (String)pathVariables.get("id");
		
		
		ServletContext sc = request.getServletContext();
		
		BlogVo blogVo = blogService.findById(blogId);
		
		sc.setAttribute("blog", blogVo);
		
		return true;
	}
	
}
