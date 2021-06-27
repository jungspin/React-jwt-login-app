package com.cos.authjwt.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cos.authjwt.domain.user.User;
import com.cos.authjwt.mapper.UserMapper;

@Repository
public class UserRepository {
	
	@Autowired
	private UserMapper userMapper;
	
	public int insert(User user) {
		return userMapper.insert(user);
	}
	
	public User findById(int id) {
		return userMapper.findById(id);
	}
	
	public User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}

}
