package com.home.myblog.config;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.home.myblog.intercepors.LoginIntercepor;

@Configuration
public class WebConfigurer implements WebMvcConfigurer{
	
	//这个方法是用来注册拦截器的，自定义的拦截器类需要在这里注册才能生效
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		HandlerInterceptor intercepor = new LoginIntercepor();
		List<String> list = new ArrayList<String>();
		list.add("/mycss/**");
		list.add("/myimages/**");
		list.add("/myjs/**");
		
		list.add("/users/login");
		list.add("/users/reg");
		
		list.add("/index.html");
		list.add("/regpage.html");
		registry.addInterceptor(intercepor).addPathPatterns("/**").excludePathPatterns(list);
	}
}
