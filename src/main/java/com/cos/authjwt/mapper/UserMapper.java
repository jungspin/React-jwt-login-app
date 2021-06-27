package com.cos.authjwt.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.cos.authjwt.domain.user.User;

@Mapper // 이거 달아줘야도ㅑㅐ!??!?!?!!??!!?!?!? 강의확인해봐
public interface UserMapper {
	
	
	public int insert(User user);
	
	
	public User findById(int id);
	
	
	public User findByUsername(String username);


}
