package com.douzone.config.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.douzone.jblog.interceptor.BlogAdminInterceptor;
import com.douzone.jblog.interceptor.BlogInterceptor;
import com.douzone.jblog.security.AuthInterceptor;
import com.douzone.jblog.security.AuthUserHandlerMethodArgumentResolver;
import com.douzone.jblog.security.LoginInterceptor;
import com.douzone.jblog.security.LogoutInterceptor;

@Configuration
public class SecurityConfig implements WebMvcConfigurer {

	// Argument Resolver
	@Bean
	public HandlerMethodArgumentResolver handlerMethodArgumentResolver() {
		return new AuthUserHandlerMethodArgumentResolver();
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(handlerMethodArgumentResolver());
	}

	// Security Interceptors
	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}

	@Bean
	public HandlerInterceptor logoutInterceptor() {
		return new LogoutInterceptor();
	}

	@Bean
	public HandlerInterceptor authInterceptor() {
		return new AuthInterceptor();
	}

	@Bean
	public HandlerInterceptor blogAdminInterceptor() {
		return new BlogAdminInterceptor();
	}

	@Bean
	public HandlerInterceptor blogInterceptor() {
		return new BlogInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor()).addPathPatterns("/user/auth");

		registry.addInterceptor(logoutInterceptor()).addPathPatterns("/user/logout");

		registry.addInterceptor(authInterceptor()).addPathPatterns("/{id}/admin/**").excludePathPatterns("/assets/**")
				.excludePathPatterns("/user/auth").excludePathPatterns("/user/logout");

		registry.addInterceptor(blogAdminInterceptor()).addPathPatterns("/{id}/**").excludePathPatterns("/assets/**")
				.excludePathPatterns("/user/auth").excludePathPatterns("/user/logout");

		registry.addInterceptor(blogInterceptor()).addPathPatterns("/{id}").excludePathPatterns("/assets/**")
				.excludePathPatterns("/user/auth").excludePathPatterns("/user/logout");

	}

}
