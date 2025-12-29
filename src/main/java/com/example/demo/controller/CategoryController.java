package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/category-list")
	public List<CategoryDto> listAllCategory(){
		return categoryService.listAllCategories()
				.stream()
				.map(this::toCategoryDto)
				.toList();
	}
	
	record CategoryRequest(String categoryName) {}
	
	record CategoryDto(long id, String categoryName) {}
	
	public CategoryDto toCategoryDto(Category category) {
		return new CategoryDto(category.getId(),
				category.getCategoryName());
	}
	
	@PostMapping("/create-category")
	public ResponseEntity<String> 
		createCategory(@RequestBody CategoryRequest request){
		String returnString = categoryService.createCategory(request.categoryName);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(returnString);
	}

}
