package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CategoryDao;
import com.example.demo.entity.Category;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public List<Category> listAllCategories(){
		return categoryDao.findAll();
	}
	
	public String createCategory(String categoryName) {
		Category category = new Category();
		category.setCategoryName(categoryName);
		categoryDao.save(category);
		return "New category created successfully.";
	}

}
