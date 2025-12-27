package com.example.demo.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private BigDecimal price;
	private int quantity;
	private String image;
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<ProductOrder> productOrders = new ArrayList<>();
	
	public Product(String name, BigDecimal price, int quantity, String image) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.image = image;
	}
	
}