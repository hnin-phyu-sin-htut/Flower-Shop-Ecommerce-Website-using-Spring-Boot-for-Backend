package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
	
	private Long id;
	private LocalDate orderDate;
	private String orderNumber;
	private BigDecimal totalPrice;
	private List<ProductOrderDto> products;

}
