package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	public record LoginRequest(String username, String password) {}
	
	@PostMapping("/login")
	public ResponseEntity<LoginDto> login(@RequestBody LoginRequest loginRequest){
		LoginDto loginDto = authService.login(loginRequest.username, loginRequest.password);
		return ResponseEntity.ok().body(loginDto);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
		String response = authService.register(
				registerDto.username(), 
				registerDto.password(), 
				registerDto.email(), 
				registerDto.phone(), 
				registerDto.address(), 
				registerDto.userType());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(response);
	}

}
