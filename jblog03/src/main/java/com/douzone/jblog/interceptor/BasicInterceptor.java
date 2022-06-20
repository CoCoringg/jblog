package com.douzone.jblog.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;

public class BasicInterceptor implements HandlerInterceptor {
	
	@Autowired
	private BlogService blogService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		ServletContext sc = request.getServletContext();
		
		HttpSession session = request.getSession();
		UserVo userVo = (UserVo) session.getAttribute("authUser");
		BlogVo blogVo = blogService.findById(userVo.getId());
		sc.setAttribute("blog", blogVo);
		
		return true;
	}
	
}
