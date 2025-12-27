package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.OrderDto;
import com.example.demo.dto.ProductOrderDto;
import com.example.demo.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/checkout")
	public ResponseEntity<OrderDto> checkout(
			@RequestBody List<ProductOrderDto> request,
			@AuthenticationPrincipal UserDetails userDetails
			) {
	    OrderDto orderInfo = cartService.createOrder(request, userDetails.getUsername());
	    return ResponseEntity.ok(orderInfo);
	}

}
