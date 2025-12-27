package com.example.demo.dto;

public record RegisterDto(
			String username,
			String password,
			String email,
			String phone,
			String address,
			String userType
		) {

}
