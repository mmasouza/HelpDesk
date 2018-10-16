package com.home.helpdesk.api.service;

import org.springframework.data.domain.Page;

import com.home.helpdesk.api.entity.User;

public interface UserService {
	
	User findByEmail(String email);

	User createOrUpdate(User user);
	
	User fundById(String id);
	
	void delete(String id);
	
	Page<User> findAll(int page, int count);
}
