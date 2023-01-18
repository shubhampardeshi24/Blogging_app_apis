package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CategoryDTO;
import com.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/apis/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;
	
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO dto)
	{
		CategoryDTO createCategory = service.createCategory(dto);
		return new ResponseEntity<CategoryDTO>(createCategory, HttpStatus.CREATED);
	}
	
	//update
	
	@PutMapping("/{categoryID}")//path uri variable
	public ResponseEntity<CategoryDTO> updateCategory(@Valid  @RequestBody CategoryDTO dto, @PathVariable Integer categoryID )
	{
		CategoryDTO updateCategory = service.updateCategory(dto, categoryID);
		
		return new ResponseEntity<CategoryDTO>(updateCategory, HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{categoryID}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryID)
	{
		service.deleteCategory(categoryID);

		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully", true), HttpStatus.OK);
	}
	//get
	@GetMapping("/{categoryID}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer categoryID)
	{
		return new ResponseEntity<CategoryDTO>(service.getCategory(categoryID), HttpStatus.OK);
	}
	
	
	//getall
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getAllCategory()
	{
		List<CategoryDTO> allCategory = service.getAllCategory();
		return ResponseEntity.ok(allCategory);
	}
	
	
}
