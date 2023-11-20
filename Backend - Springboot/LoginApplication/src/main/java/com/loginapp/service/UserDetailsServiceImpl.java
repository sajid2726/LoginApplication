package com.loginapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loginapp.entity.User;
import com.loginapp.repo.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User  user = userRepo.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("User Not Found: " + username, null);
		}
		
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword()
				,new ArrayList<>());
	}

}
