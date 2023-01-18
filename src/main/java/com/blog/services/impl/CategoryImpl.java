package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.exceptions.ResourcesNotFoundException;
import com.blog.payloads.CategoryDTO;
import com.blog.repositories.CategoryRepo;
import com.blog.services.CategoryService;
@Service
public class CategoryImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {

		Category category = mapper.map(categoryDTO, Category.class);
		Category addedCategory = categoryRepo.save(category);
		return mapper.map(addedCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryID) {
		Category cat = categoryRepo.findById(categoryID)
				.orElseThrow(() -> new ResourcesNotFoundException("Category", "Category ID", categoryID));
		
		cat.setCategoryDescription(categoryDTO.getCategoryDescription());
		cat.setCategoryTitle(categoryDTO.getCategoryTitle());
		Category saveCat = categoryRepo.save(cat);
		return mapper.map(saveCat, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Integer categoryID) {
		Category cat = categoryRepo.findById(categoryID)
				.orElseThrow(() -> new ResourcesNotFoundException("Category", "Category ID", categoryID));
		categoryRepo.delete(cat);
	}

	@Override
	public CategoryDTO getCategory(Integer categoryID) {
		Category cat = categoryRepo.findById(categoryID)
				.orElseThrow(() -> new ResourcesNotFoundException("Category", "Category ID", categoryID));
		return mapper.map(cat, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<Category> categories = categoryRepo.findAll();
		
		return categories.stream().map(category -> mapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
	}

}
