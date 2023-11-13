package com.example.bookmark.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookmark.entity.Category;
import com.example.bookmark.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	
	@Override
	public Optional<Category> findById(Long id){
		return categoryRepository.findById(id);
	}
	
	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}
	
	@Override
	public void deleteById(Long id) {
		categoryRepository.deleteById(id);
	}
}
