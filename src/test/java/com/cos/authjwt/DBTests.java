package com.cos.authjwt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cos.authjwt.domain.user.User;
import com.cos.authjwt.service.UserService;

import lombok.Setter;
// 테스트 하고 싶은데 레거시랑은 달라보여..더 알아봐야겠음

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DBTests {
	
	@Setter(onMethod_ = @Autowired)
	private UserService userService;
	
	@Test
	public void insertTest() {
		User user = new User();
		user.setUsername("bori");
		user.setPassword("1234");
		user.setEmail("bori@nate.com");
		user.setRole("GUEST");
		int result = userService.insert(user);
		
		assertEquals(1, result);
	}
}
