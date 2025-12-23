package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.CartItemDao;
import com.example.demo.dto.CartItemDto;
import com.example.demo.entity.CartItem;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartServiceImple implements CartService {
	
	private final CartItemDao cartItemDao;

	public CartServiceImple(CartItemDao cartItemDao) {
		super();
		this.cartItemDao = cartItemDao;
	}

	@Override
	public void saveCart(List<CartItemDto> cartItems) {
		
		for(CartItemDto dto : cartItems) {
			CartItem cartItem = new CartItem();
			cartItem.setId(dto.getId());
			cartItem.setName(dto.getName());
			cartItem.setPrice(dto.getPrice());
			cartItem.setQuantity(dto.getQuantity());
			cartItem.setImage(dto.getImage());
			
			cartItemDao.save(cartItem);
		}
	}

}
