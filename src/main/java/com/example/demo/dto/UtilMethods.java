package com.example.demo.dto;

import java.math.BigDecimal;

import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductOrder;

public class UtilMethods {

	public static OrderDto toOrderDto(Order order) {
	    OrderDto dto = new OrderDto();

	    dto.setId(order.getId());
	    dto.setOrderDate(order.getOrderDate());
	    dto.setOrderNumber(order.getOrderNumber());
	    dto.setTotalPrice(order.getTotalPrice());

	    dto.setProducts(
	        order.getProductOrders()
	             .stream()
	             .map(UtilMethods::toProductOrderDto)
	             .toList()
	    );
	    return dto;
	}
	
	public static ProductDto toProductDto(Product product) {
	    ProductDto dto = new ProductDto();

	    dto.setId(product.getId());
	    dto.setName(product.getName());
	    dto.setPrice(product.getPrice());
	    dto.setImage(product.getImage());
	    dto.setQuantity(0);
	    return dto;
	}
	
	public static ProductOrderDto toProductOrderDto(ProductOrder po) {
	    ProductOrderDto dto = new ProductOrderDto();

	    dto.setId(po.getProduct().getId());
	    dto.setProductName(po.getProduct().getName());
	    dto.setQuantity(po.getQuantity());

	    dto.setSubTotal(
	        po.getProduct()
	          .getPrice()
	          .multiply(BigDecimal.valueOf(po.getQuantity()))
	    );

	    return dto;
	}
	
}
