package com.example.demo.entity;

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
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {
	
	private String phone;
	private String address;
	
	public Customer(String username, String password, String email, String phone, String address) {
		super(username, password, email);
		this.phone = phone;
		this.address = address;
	}

}
