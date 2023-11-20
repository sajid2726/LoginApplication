package com.loginapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loginapp.dto.HelloResponse;

@RestController
@RequestMapping("/api")
public class HelloController {
	
	@GetMapping("/hello")
	public HelloResponse sayHello(){
		return new HelloResponse("Hello from jwt authentication");
		
	}

}
