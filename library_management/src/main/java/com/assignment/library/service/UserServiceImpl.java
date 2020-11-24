package com.assignment.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.library.model.User;
import com.assignment.library.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User getUser(Long userId) {
		User user = null;
		if(userId != null) {
			user = userRepository.findById(userId).get();
		}
		return user;
	}
	
	public User updateUser(User user) {
		if(user != null) {
			user = userRepository.save(user);
		}
		return user;
	}

}
