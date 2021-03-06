package com.douzone.jblog.security;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {
	
	@Autowired
	private BlogService blogService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 1. Handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			// DefaultServletHandler가 처리하는 정적 자원
			return true;
		}
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		// 3. Handler Method의 @Auth 받아보기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		// 4. Handler Method에 @Auth가 없으면 Type에 붙어 있는 지 확인
		if(auth == null ) {
			/* 과제 */
			auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
		}
		
		// 5. Type과 Handler Method 모두에 @Auth가 안붙어 있는 경우
		if(auth == null) {
			return true;
		}
		
		// 6. Handler Method에 @Auth가 붙어 있기 때문에 인증(Authentication) 여부 확인
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		// 7. @Auth가 적용되어 있지만 인증이 되어 있지 않음
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);		
		String blogId = (String)pathVariables.get("id");
		
		if(!blogId.equals(authUser.getId())) {
			response.sendRedirect(request.getContextPath()+ "/" +blogId);
			return false;
		}
		
		ServletContext sc = request.getServletContext();

		BlogVo blogVo = blogService.findById(authUser.getId());
		List<CategoryVo> categoryList = blogService.findByCategoryAndPost(authUser.getId());
		
		List<CategoryVo> categorySelect = blogService.findByCategory(authUser.getId());
		
		sc.setAttribute("blog", blogVo);
		sc.setAttribute("categoryList", categoryList);
		sc.setAttribute("categorySelect", categorySelect);
		
		return true;
	}

}
