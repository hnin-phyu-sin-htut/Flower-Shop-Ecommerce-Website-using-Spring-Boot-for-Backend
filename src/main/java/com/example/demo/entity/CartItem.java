package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private double price;
	private int quantity;
	private String image;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public CartItem(String name, double price, int quantity, String image) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.image = image;
	}
	
}