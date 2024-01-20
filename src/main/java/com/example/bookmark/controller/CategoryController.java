package com.example.bookmark.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.bookmark.entity.Category;
import com.example.bookmark.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public String listCategories(Model model) {
		List<Category> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		return "category/categoryList";
	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("category", new Category());
		return "category/addCategory";
	}

	@PostMapping
	public String saveCategory(@ModelAttribute Category category, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (categoryService.isCategoryNameExist(category.getName())) {
	        result.rejectValue("name", "error.category", "このカテゴリー名はすでに存在しています");
	        return "category/addCategory";
	    }
		categoryService.save(category);
		redirectAttributes.addFlashAttribute("success", "カテゴリーが正常に追加されました");
		return "redirect:/category";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model) {
		Category category = categoryService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
		model.addAttribute("category", category);
		return "category/edit";
	}

	@PostMapping("/update/{id}")
	public String updateCategory(@PathVariable Long id, @ModelAttribute Category category,
			RedirectAttributes redirectAttributes) {
		category.setId(id);
		categoryService.save(category);
		redirectAttributes.addFlashAttribute("success", "Category updated successfully!");
		return "redirect:/category";
	}

	@GetMapping("/delete/{id}")
	public String deleteCategory(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		categoryService.deleteById(id);
		redirectAttributes.addFlashAttribute("success", "Category deleted successfully!");
		return "redirect:/category";
	}
}