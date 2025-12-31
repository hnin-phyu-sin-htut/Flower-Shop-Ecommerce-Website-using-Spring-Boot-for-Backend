package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.controller.ProductController.ProductEditResquest;
import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.ProductDao;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.UtilMethods;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.exception.CategoryNotFoundException;
import com.example.demo.exception.ProductNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ProductDao productDao;
	
	public ProductDto findByProductName(String productName) {
        Product product = productDao.findAll()
                                    .stream()
                                    .filter(p -> p.getName().equals(productName))
                                    .findFirst()
                                    .orElseThrow(ProductNotFoundException::new);
        return UtilMethods.toProductDto(product);
    }
	
	public List<ProductDto> listAllProducts() {
        List<Product> products = productDao.findAll();
        return products.stream()
                       .map(UtilMethods::toProductDto)
                       .toList();
    }

	public List<ProductDto> listAllProductsByCategory(String categoryName) {
        List<Product> products = productDao.findAll()
                                           .stream()
                                           .filter(p -> p.getCategory().getCategoryName().equals(categoryName))
                                           .toList();
        return products.stream()
                       .map(UtilMethods::toProductDto)
                       .toList();
    }
	
	public ProductDto createProduct(String name, BigDecimal price, int quantity, MultipartFile image, Long categoryId) 
			throws Exception {		
	    Category category = categoryDao.findById(categoryId)
	            .orElseThrow(() -> new CategoryNotFoundException("Category not found."));

	    Product product = new Product();
	    product.setName(name);
	    product.setPrice(price);
	    product.setQuantity(quantity);

	    if (image != null) {
			product.setImage(image.getBytes());
		}
	    product.setCategory(category);

	    productDao.save(product);
	    category.addProduct(product);
	    categoryDao.save(category);

	    return new ProductDto(
	    		product.getId(),
	    	    product.getName(),
	    	    product.getPrice(),
	    	    product.getQuantity(),
	    	    product.getImage(),
	    	    product.getCategory().getId(),
	    	    product.getCategory().getCategoryName()
	    	);

	}
	
	public Product findProduct(long id) {
		return productDao.findById(id).orElseThrow(ProductNotFoundException::new);
	}
	
	public ProductDto editProduct(long id, String name, BigDecimal price, MultipartFile image) {
	    Product product = findProduct(id);

	    product.setName(name);
	    product.setPrice(price);

	    try {
	        if (image != null && !image.isEmpty()) {
	            product.setImage(image.getBytes());
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to update image!");
	    }

	    product = productDao.save(product);

	    return new ProductDto(
	        product.getId(),
	        product.getName(),
	        product.getPrice(),
	        product.getQuantity(),
	        product.getImage(),
	        product.getCategory().getId(),
	        product.getCategory().getCategoryName()
	    );
	}
	
	public void deleteProduct(Long id) {
	    Product product = findProduct(id);
	    productDao.delete(product);
	}

}
