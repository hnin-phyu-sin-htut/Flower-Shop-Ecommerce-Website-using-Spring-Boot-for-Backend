package com.example.demo.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOrderDto {
	
	private Long id;
	private String productName;
	private int quantity;
	private BigDecimal subTotal;
	
}
