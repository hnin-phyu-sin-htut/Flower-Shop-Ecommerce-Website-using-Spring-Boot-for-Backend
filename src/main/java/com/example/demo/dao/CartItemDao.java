package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CartItem;

public interface CartItemDao extends JpaRepository<CartItem, Long> {
	
}
