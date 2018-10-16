package com.home.helpdesk.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.home.helpdesk.api.entity.User;
import com.home.helpdesk.api.repository.UserRespository;
import com.home.helpdesk.api.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRespository userRepository;
	
	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User createOrUpdate(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public User fundById(String id) {
		return this.fundById(id);
	}

	@Override
	public void delete(String id) {
		this.userRepository.deleteById(id);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<User> findAll(int page, int count) {
		@SuppressWarnings("deprecation")
		Pageable pages = new PageRequest(page, count);
		return this.userRepository.findAll(pages);
	}

}
