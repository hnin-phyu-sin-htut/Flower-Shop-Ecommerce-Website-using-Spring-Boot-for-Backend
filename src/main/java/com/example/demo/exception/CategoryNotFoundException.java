package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CategoryNotFoundException extends ResponseStatusException {

	public CategoryNotFoundException(String msg) {
		super(HttpStatus.BAD_REQUEST, msg);
	}

}
