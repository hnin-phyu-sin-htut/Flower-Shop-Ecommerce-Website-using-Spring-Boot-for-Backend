package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Admin;

public interface AdminDao extends JpaRepository<Admin, Long> {
	
	Optional<Admin> findByUsername(String username);

}
