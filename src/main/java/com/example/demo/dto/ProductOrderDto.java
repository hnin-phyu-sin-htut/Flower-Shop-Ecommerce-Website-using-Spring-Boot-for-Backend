package com.example.demo.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductOrderDto {
	
	private Long id;
	private String productName;
	private int quantity;
	private BigDecimal subTotal;
	
	public ProductOrderDto(String productName, int quantity, BigDecimal subTotal) {
		super();
		this.productName = productName;
		this.quantity = quantity;
		this.subTotal = subTotal;
	}
	
}
