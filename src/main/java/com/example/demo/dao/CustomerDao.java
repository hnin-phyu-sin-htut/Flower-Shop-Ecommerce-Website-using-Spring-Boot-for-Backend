package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findByUsername(String name);

}
