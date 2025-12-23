package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CartItemDto;

public interface CartService {	
	
	void saveCart(List<CartItemDto> cartItems);
	
}
