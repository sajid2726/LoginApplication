package com.loginapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.loginapp.dto.SignupRequest;
import com.loginapp.dto.UserDTO;
import com.loginapp.entity.User;
import com.loginapp.repo.UserRepo;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDTO createUser(SignupRequest request) {
		User user = new User();
		user.setEmail(request.getEmail());
		user.setName(request. getName());
//		user.setPhone(request.getPhone());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		User createdUser = userRepo.save(user);
		UserDTO createdUserDTO = new UserDTO();
		createdUserDTO.setEmail(createdUser.getEmail());
		createdUserDTO.setName(createdUser.getName());
//		createdUserDTO.setPhone(createdUser.getPhone());	
		return createdUserDTO;
	}

}
