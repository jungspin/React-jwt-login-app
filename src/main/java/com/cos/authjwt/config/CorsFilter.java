package com.cos.authjwt.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("cors 필터 작동");
		
		//HttpServletRequest req = (HttpServletRequest) request; // 다운캐스팅
		HttpServletResponse resp = (HttpServletResponse) response;
		
		resp.setHeader("Access-Control-Allow-Origin", "*"); // http://localhost:3000 으로 해주는게 더 좋음
		resp.setHeader("Access-Control-Allow-Methods", "*"); // 정확하게 적어주는게 더 좋음
		resp.setHeader("Access-Control-Allow-Headers", "*");
		resp.setHeader("Access-Control-Expose-Headers", "*");
		chain.doFilter(request, response);
		
	}

}
