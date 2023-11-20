package com.loginapp.service;

import com.loginapp.dto.SignupRequest;
import com.loginapp.dto.UserDTO;

public interface AuthService {

	UserDTO createUser(SignupRequest request);

}
