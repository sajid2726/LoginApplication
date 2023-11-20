package com.loginapp.controller;

import java.io.IOError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.loginapp.dto.AuthRequest;
import com.loginapp.dto.AuthResponse;
import com.loginapp.service.UserDetailsServiceImpl;
import com.loginapp.util.JwtUtil;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/authentication")
	public AuthResponse createAuthToken(@RequestBody AuthRequest authRequest, HttpServletResponse response)
			throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException,
			java.io.IOException {

		try {

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect credentials");
		} catch (DisabledException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not created, register first");
			return null;
		}

		final UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authRequest.getEmail());
		final String jwt = jwtUtil.generateToken(userDetails.getUsername());
		return new AuthResponse(jwt);

	}

}
