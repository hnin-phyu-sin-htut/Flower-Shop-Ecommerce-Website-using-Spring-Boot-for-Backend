package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {
	
	@Column(nullable = false)
	private String phone;

	public Admin(String username, String password, String email, String phone) {
		super(username, password, email);
		this.phone = phone;
	}
	
}
