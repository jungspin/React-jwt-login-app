package com.cos.authjwt.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.authjwt.domain.user.User;
import com.cos.authjwt.service.UserService;
import com.cos.authjwt.web.dto.user.CMRespDTO;


@RestController // 데이터만 리턴
public class UserController {

	/*
	 * public void login() {
	 * 
	 * }
	 */
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user")
	public ResponseEntity<?> userinfo() {
		// 토큰 없으면 못 오게 할거야
		
		User principal = (User) session.getAttribute("principal");
		System.out.println("로그인 된 사용자 : " + principal);
		
		// 데이터 돌려주기
		User user = userService.findById(principal.getId());
		user.setPassword(null);
		System.out.println("user 정보 : " + user);
		//User user = new User(1, "ssar", "null", "ssar@nate.com", "GUEST");
		return new ResponseEntity<>(new CMRespDTO<User>(1, "find OK", user ), HttpStatus.OK);
	}
}
