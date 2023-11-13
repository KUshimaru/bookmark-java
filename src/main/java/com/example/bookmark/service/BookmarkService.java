package com.example.bookmark.service;

import java.util.List;
import java.util.Optional;

import com.example.bookmark.entity.Bookmark;

public interface BookmarkService {
	List<Bookmark> findAll();
	Optional<Bookmark> findById(Long id);
	Bookmark save(Bookmark bookmark);
	void deleteById(Long id);
}
