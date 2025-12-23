package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
	private Long id;
	private String name;
	private double price;
	private int quantity;
	private String image;
}