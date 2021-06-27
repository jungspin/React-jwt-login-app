package com.cos.authjwt.config.filter.jwt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cos.authjwt.domain.user.User;

// 권한을 인가해주는 친구 (문지기)
// /post/**, /user/** 아무나 접근 못하는 곳을 접근해야할 때
public class JwtAuthorizationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("사용자 인가 필터 JwtAuthorizationFilter 동작 시작");
		

		HttpServletRequest req = (HttpServletRequest) request; // 다운캐스팅
		HttpServletResponse resp = (HttpServletResponse) response;

		String jwtToken = req.getHeader(JwtProps.HEADER);

		System.out.println("토큰 : " + jwtToken);

		if (jwtToken == null) {
			PrintWriter out = resp.getWriter();
			out.println("jwtToken not found");
			out.flush();
		} else {
			jwtToken = jwtToken.replace(JwtProps.AUTH, ""); // Bearer 없어야 검증이 가능!!
			System.out.println("jwtToken : " + jwtToken);

			try {
				DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(JwtProps.SECRET))
						.build()
						.verify(jwtToken);

				Integer userId = decodedJWT.getClaim("id").asInt(); // claim 타입이라서 Integer 로 변환해줘야됨
				String role = decodedJWT.getClaim("role").asString(); // 마찬가지

				User loginUser = new User(userId, null, null, null, role);

				// 임시 세션 만들기 -> 한번쓰고 버리는 세션
				HttpSession session = req.getSession();
				session.setAttribute("principal", loginUser);
				System.out.println("토큰 검증 성공");
				chain.doFilter(req, resp);
			} catch (Exception e) {
				PrintWriter out = resp.getWriter();
				out.println("verify fail");
				out.flush();
			}

		}

	}

}
