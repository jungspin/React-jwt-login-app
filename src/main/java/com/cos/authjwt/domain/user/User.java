package com.cos.authjwt.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private Integer id; // auto_increment
	private String username;
	private String password;
	private String email;
	private String role; // admin, guest -> Enum 으로 관리하면 좋다
}
