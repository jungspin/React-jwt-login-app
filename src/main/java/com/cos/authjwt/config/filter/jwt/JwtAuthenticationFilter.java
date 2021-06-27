package com.cos.authjwt.config.filter.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cos.authjwt.domain.user.User;
import com.cos.authjwt.mapper.UserMapper;
import com.cos.authjwt.web.dto.user.CMRespDTO;
import com.cos.authjwt.web.dto.user.LoginReqDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
// username, password 받아서 디비에서 확인해서 JWT 토큰 만들어서 응답해주는 친구
// 동작시점 /login + POST 요청일 때만 동작!!
public class JwtAuthenticationFilter implements Filter {

	
	private UserMapper userMapper;

	/*
	 * public JwtAuthenticationFilter( 유저 매퍼 받기 UserMapper userMapper) {
	 * 
	 * }
	 */

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request; // 다운캐스팅
		HttpServletResponse resp = (HttpServletResponse) response;

		if (!req.getMethod().equals("POST")) {
			resp.setContentType("text/plain; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("잘못된 요청입니다.");
			out.flush();
			return;
		}

		// POST 요청이 올때만 실행!!!
		System.out.println("로그인 인증 필터 JwtAuthenticationFilter 동작 시작");

		// 1. username, password 받아야함 (req) -> json 으로 받을것 - 버퍼로 읽기 (ObjectMapper 로
		// LoginReqDto에 매핑)
		ObjectMapper mapper = new ObjectMapper();
		LoginReqDTO loginReqDTO = mapper.readValue(req.getInputStream(), LoginReqDTO.class);
		System.out.println("다운 받은 데이터 : " + loginReqDTO);
		// 2. DB 질의 (select) -> if (ssar, 1234) 체크
		if (userMapper == null) {
			System.out.println("userMapper : " + userMapper);
			return;
		}
		
		// 로그인 하는 부분 -> 일치하는 데이터 있는지 확인
		User userEntity = userMapper.findByUsername(loginReqDTO.getUsername());
		// ~~Entity : 디비에서 셀렉해서 받아온 애라는 것을 이름으로 표현하기
		// User userEntity = new User(1, "ssar", "1234", "ssar@nate.com", "GUEST");
		
		userEntity.setPassword(null);

		System.out.println("userEntity : " + userEntity);

		if (userEntity == null) {
			resp.setContentType("text/plain; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("인증 되지 않았습니다. 다시 인증해주세요.");
			out.flush();
			return;
		} else {
			// 토큰을 응답 (bulider pattern)
			String jwtToken = JWT.create().withSubject(JwtProps.SUBJECT) // 토큰의 제목 . 정해진 키값이기때문에 함수가 있는거임
					.withExpiresAt(new Date(System.currentTimeMillis() + JwtProps.EXPIRESAT)) // 만료시간 1시간
					.withClaim("id", userEntity.getId()).withClaim("role", userEntity.getRole())
					.sign(Algorithm.HMAC512(JwtProps.SECRET));

			// 헤더 키값 = RFC 문서
			resp.setHeader(JwtProps.HEADER, JwtProps.AUTH + jwtToken); // Bearer 붙여서 보내니까 두번 나왔엉 테스트 해보기!!
			resp.setContentType("application/json; charset=utf-8");
			PrintWriter out = resp.getWriter();
			// User user = new User(1, "ssar", "1234", "ssar@nate.com", "GUEST");
			//ResponseEntity<CMRespDTO> responseEntity = new ResponseEntity<CMRespDTO>(new CMRespDTO<User>(1, "login success", userEntity ), HttpStatus.OK);
			
			CMRespDTO<User> cmRespDTO = new CMRespDTO<User>(1, "login success", userEntity );
			String cmRespDTOJson = mapper.writeValueAsString(cmRespDTO);
			System.out.println(cmRespDTOJson);
			out.print(cmRespDTOJson); // CMRespDTO -> 응답을 오브젝트로 날려서 상태로 관리해바.....
			out.flush();

		}
		// -> 원형 (1, ssar, 1234, ssar@nate.com, guest)
		// 3. JWT 토큰 생성 -> (1, guest)
		// 4. 응답하면 끝!!!

		/*
		 * BufferedReader br = req.getReader(); // getReader 가 BufferedReader를 구현한 구현체
		 * StringBuilder sb = new StringBuilder(); String data = null; while ((data =
		 * br.readLine()) != null) { sb.append(data); }
		 * 
		 * System.out.println("다운 받은 데이터 : " + sb.toString());
		 * 
		 * ObjectMapper mapper = new ObjectMapper(); // String json =
		 * "{ \"username\": \"ssar\", \"password\": \"1234\"}"; LoginReqDTO loginReqDTO
		 * = mapper.readValue(sb.toString(), LoginReqDTO.class);
		 * System.out.println(loginReqDTO.getUsername());
		 */
	}
}
