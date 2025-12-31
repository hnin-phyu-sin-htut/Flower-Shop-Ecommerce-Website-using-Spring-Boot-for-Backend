package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	    @RequestParam("categoryId") Long categoryId
	) throws Exception {

	    ProductDto created = productService.createProduct(name, price, quantity, image, categoryId);
	    return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
	
	public record ProductEditResquest(String name, BigDecimal price, String image) {}
	
	@PutMapping(value = "/edit/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ProductDto editProduct(
	        @PathVariable long id,
	        @RequestParam String name,
	        @RequestParam BigDecimal price,
	        @RequestParam(required = false) MultipartFile image
	) {
	    return productService.editProduct(id, name, price, image);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	    productService.deleteProduct(id);
	    return ResponseEntity.noContent().build();
	}

}
