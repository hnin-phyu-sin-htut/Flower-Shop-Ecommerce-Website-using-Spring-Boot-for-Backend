package com.example.demo.dto;

import java.math.BigDecimal;
import java.util.Base64;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductDto {
	
	private Long id;
	private String name;
	private BigDecimal price;
	private int quantity;
	private String image;
	private Long categoryId;
	
	public ProductDto(Long id, String name, BigDecimal price, int quantity, byte[] image, Long categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.image = image != null ? Base64
				.getEncoder().encodeToString(image) : null;
		this.categoryId = categoryId;
	}
	
}