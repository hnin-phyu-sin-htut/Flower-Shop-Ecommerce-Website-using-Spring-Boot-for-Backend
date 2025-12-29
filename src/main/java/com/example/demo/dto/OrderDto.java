package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
	
	private Long id;
	private LocalDate orderDate;
	private String orderNumber;
	private BigDecimal totalPrice;
	private List<ProductOrderDto> products;
	
	public OrderDto(LocalDate orderDate, String orderNumber, BigDecimal totalPrice,
			List<ProductOrderDto> products) {
		super();
		this.orderDate = orderDate;
		this.orderNumber = orderNumber;
		this.totalPrice = totalPrice;
		this.products = products;
	}

}
