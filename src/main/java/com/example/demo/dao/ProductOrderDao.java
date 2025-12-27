package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ProductOrder;

public interface ProductOrderDao extends JpaRepository<ProductOrder, Long> {
	
}
