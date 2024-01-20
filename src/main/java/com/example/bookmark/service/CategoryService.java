package com.example.bookmark.service;

import java.util.List;
import java.util.Optional;

import com.example.bookmark.entity.Category;

public interface CategoryService {
	List<Category> findAll();
	Optional<Category> findById(Long id);
	Category save(Category category);
	void deleteById(Long id);
	boolean isCategoryNameExist(String name);
}
