package com.home.helpdesk.api.securyti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.home.helpdesk.api.entity.User;
import com.home.helpdesk.api.securyti.jwt.JwtUserFactory;
import com.home.helpdesk.api.service.UserService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException(String.format("Nenhum usuário encontrado com este e-mail '%s'. ", email));
		}else {
			return JwtUserFactory.create(user);
		}
	}

}
