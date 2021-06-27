package com.cos.authjwt.web.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 요청을 받는 DTO

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginReqDTO { // 로그인할 때 이거 쓰라고 했지?
	private String username;
	private String password;
}
