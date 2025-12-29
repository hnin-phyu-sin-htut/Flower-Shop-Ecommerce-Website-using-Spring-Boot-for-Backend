package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;

public interface ProductDao extends JpaRepository<Product, Long> {
	
	@Query("""
		    select new com.example.demo.dto.ProductDto(
		        p.id,
		        p.name,
		        p.price,
		        p.quantity,
		        p.image,
		        p.category.id
		    )
		    from Product p
		    where p.name = :productName
		""")
		Optional<ProductDto> findByProductName(String productName);

		@Query("""
		    select new com.example.demo.dto.ProductDto(
		        p.id,
		        p.name,
		        p.price,
		        p.quantity,
		        p.image,
		        p.category.id
		    )
		    from Product p
		    join p.category c
		    where c.categoryName = :categoryName
		""")
		List<ProductDto> findAllProductsByCategory(String categoryName);

		@Query("""
		    select new com.example.demo.dto.ProductDto(
		        p.id,
		        p.name,
		        p.price,
		        p.quantity,
		        p.image,
		        p.category.id
		    )
		    from Product p
		""")
		List<ProductDto> findAllProducts();

}
