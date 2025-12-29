package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
	
	@Column(nullable = false)
	private String phone;
	
	@Column(nullable = false)
	private String address;
	
	@OneToMany(mappedBy = "customer")
	private List<Order> orders = new ArrayList<>();
	
	public void addOrder(Order order) {
		order.setCustomer(this);
		orders.add(order);
	}
	
	public Customer(String username, String password, String email, String phone, String address) {
		super(username, password, email);
		this.phone = phone;
		this.address = address;
	}

}
