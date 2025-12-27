package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	public static ProductDto toDto(Product product) {
		ProductDto pro = new ProductDto();
		BeanUtils.copyProperties(product, pro);
		return pro;
	}
	
	@GetMapping("/products")
	public List<ProductDto> listAllProducts(){
		return productService.listAllProducts()
		.stream()
		.map(ProductController::toDto)
		.toList();
	}

}
