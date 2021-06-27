package com.cos.authjwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cos.authjwt.config.filter.jwt.JwtAuthenticationFilter;
import com.cos.authjwt.config.filter.jwt.JwtAuthorizationFilter;
import com.cos.authjwt.mapper.UserMapper;

// 진짜 쓸거
// 스프링은 필터를 xml이 아닌 클래스로 만든다
@Configuration // 스프링에서 IoC 로 등록할 수 있는 방법 중 하나 (@Compoment 계열)
public class FilterConfig {
	// 필터 순서도 정할 수 있대
	
	@Bean
	public FilterRegistrationBean<CorsFilter> CorsFilter(){
		System.out.println("corsFilter 등록됨");
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter());
		bean.addUrlPatterns("/*");
		bean.setOrder(0); // 낮은 번호부터 실행이 됨
		return bean;
	}
	
	@Autowired
	private UserMapper userMapper;
	
	@Bean // 함수의 리턴값을 IoC 등록할 수 있음 -> 쓰려면 @Configuration 해야됨
	public FilterRegistrationBean<JwtAuthenticationFilter> jwtAuthenticationFilter(){
		System.out.println("JwtAuthenticationFilter 등록됨");
		FilterRegistrationBean<JwtAuthenticationFilter> bean = new FilterRegistrationBean<JwtAuthenticationFilter>(new JwtAuthenticationFilter(userMapper));
		bean.addUrlPatterns("/login");
		bean.setOrder(1); // 낮은 번호부터 실행이 됨
		return bean;
	}
	
	@Bean // 함수의 리턴값을 IoC 등록할 수 있음 -> 쓰려면 @Configuration 해야됨
	public FilterRegistrationBean<JwtAuthorizationFilter> jwtAuthorizationFilter(){
		System.out.println("JwtAuthorizationFilter 등록됨");
		FilterRegistrationBean<JwtAuthorizationFilter> bean = new FilterRegistrationBean<JwtAuthorizationFilter>(new JwtAuthorizationFilter());
		bean.addUrlPatterns("/post/*");
		bean.addUrlPatterns("/user/*");
		bean.setOrder(2);
		return bean;
	}
	
	
	
	
}

