package com.assignment.library.service;

import com.assignment.library.model.User;

public interface UserService {

	User getUser(Long userId);
	
	User updateUser(User user);
	
}
