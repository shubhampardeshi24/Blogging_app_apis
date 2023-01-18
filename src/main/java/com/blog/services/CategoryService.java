package com.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.payloads.CategoryDTO;


public interface CategoryService {
	//create
	CategoryDTO createCategory(CategoryDTO categoryDTO);
	//update
	CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryID);
	//delete
	void deleteCategory(Integer categoryID);
	//get
	CategoryDTO getCategory(Integer categoryID);
	//getall
	List<CategoryDTO> getAllCategory();
}
