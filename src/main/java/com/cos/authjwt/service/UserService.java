package com.cos.authjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.authjwt.domain.user.User;
import com.cos.authjwt.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public int insert(User user) {
		return userRepository.insert(user);
	}

	public User findById(int id) {
		return userRepository.findById(id);
	}
	
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
