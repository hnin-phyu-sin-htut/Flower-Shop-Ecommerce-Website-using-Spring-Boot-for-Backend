package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.ProductOrderDto;

public interface CartService {	
	OrderDto createOrder(List<ProductOrderDto> orders, String username);
}
