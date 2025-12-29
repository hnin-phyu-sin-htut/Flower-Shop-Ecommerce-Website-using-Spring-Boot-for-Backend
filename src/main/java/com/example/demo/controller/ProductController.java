package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping("/products-list")
	public List<ProductDto> listAllProducts() {
		return productService.listAllProducts();
	}
	
	@GetMapping("/{categoryName}")
	public List<ProductDto> listAllProductsByCategory(@PathVariable String categoryName){
		return productService.listAllProductsByCategory(categoryName);
	}
		
	@GetMapping("/{id}/image")
	public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
	    Product product = productService.findProduct(id);

	    if (product.getImage() == null) {
	        return ResponseEntity.notFound().build();
	    }

	    return ResponseEntity.ok()
	            .contentType(MediaType.IMAGE_PNG)
	            .body(product.getImage());
	}
	
	@PostMapping("/create-product")
	public ResponseEntity<ProductDto> createProduct(
	    @RequestParam("name") String name,
	    @RequestParam("price") BigDecimal price,
	    @RequestParam("quantity") int quantity,
	    @RequestParam(value = "image", required = false) MultipartFile image,
	    @RequestParam("categoryName") String categoryName
	) throws Exception {

	    ProductDto created = productService.createProduct(name, price, quantity, image, categoryName);
	    return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	public record ProductEditResquest(String name, BigDecimal price) {}
	
	@PutMapping("/edit/{id}")
	public ProductDto editProduct(@RequestBody ProductEditResquest request,
			@PathVariable long id) {
		return productService.editProduct(id, request);
	}

}
