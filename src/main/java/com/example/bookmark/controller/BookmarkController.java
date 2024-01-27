package com.example.bookmark.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.bookmark.entity.Bookmark;
import com.example.bookmark.entity.Category;
import com.example.bookmark.service.BookmarkService;
import com.example.bookmark.service.CategoryService;

@Controller
@RequestMapping("/bookmark")
public class BookmarkController {

	private final BookmarkService bookmarkService;
	private final CategoryService categoryService;

	// コンストラクタインジェクションを使用
	public BookmarkController(BookmarkService bookmarkService, CategoryService categoryService) {
		this.bookmarkService = bookmarkService;
		this.categoryService = categoryService;
	}

	@GetMapping("/")
	public String redirectToBookmark() {
		return "redirect:/bookmark";
	}

	@GetMapping
	public String list(Model model) {
		List<Bookmark> bookmarks = bookmarkService.findAll();
		Map<Long, String> bookmarkCategories = new HashMap<>();
		for (Bookmark bookmark : bookmarks) {
			String categories = bookmark.getCategories().stream().map(Category::getName)
					.collect(Collectors.joining(","));
			bookmarkCategories.put(bookmark.getId(), categories);
		}
		model.addAttribute("bookmarks", bookmarks);
		model.addAttribute("bookmarkCategories", bookmarkCategories);
		return "list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("bookmark", new Bookmark());
		model.addAttribute("categories", categoryService.findAll());
		return "add";
	}

	@PostMapping
	public String save(@ModelAttribute Bookmark bookmark, RedirectAttributes redirectAttributes) {
		bookmarkService.save(bookmark);
		redirectAttributes.addFlashAttribute("success", "Bookmark saved successfully!");
		return "redirect:/bookmark";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("bookmark", bookmarkService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bookmark Id:" + id)));
		model.addAttribute("categories", categoryService.findAll());
		return "edit";
	}

	@PostMapping("/{id}")
	public String update(@PathVariable Long id, @ModelAttribute Bookmark bookmark,
			RedirectAttributes redirectAttributes) {
		if (bookmark.getId() == null) {
			bookmark.setId(id);
		}
		bookmarkService.save(bookmark);
		redirectAttributes.addFlashAttribute("success", "Bookmark updated successfully!");
		return "redirect:/bookmark";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		bookmarkService.deleteById(id);
		redirectAttributes.addFlashAttribute("success", "Bookmark deleted successfully!");
		return "redirect:/bookmark";
	}
}
