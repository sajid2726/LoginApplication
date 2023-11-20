package com.loginapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.loginapp.dto.SignupRequest;
import com.loginapp.dto.UserDTO;
import com.loginapp.service.AuthService;

@RestController
@CrossOrigin("http://localhost:4200/*")
public class UserController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody SignupRequest request){
		UserDTO createdUser = authService.createUser(request);
		if(createdUser==null)
			return new ResponseEntity<>("User not created", HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
}
