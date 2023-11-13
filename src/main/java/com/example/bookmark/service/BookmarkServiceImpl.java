package com.example.bookmark.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookmark.entity.Bookmark;
import com.example.bookmark.repository.BookmarkRepository;

@Service
public class BookmarkServiceImpl implements BookmarkService {
	
	@Autowired
	private BookmarkRepository bookmarkRepository;
	
	@Override
	public List<Bookmark> findAll() {
		return bookmarkRepository.findAll();
	}
	
	@Override
	public Optional<Bookmark> findById(Long id){
		return bookmarkRepository.findById(id);
	}
	
	@Override
	public Bookmark save(Bookmark bookmark) {
		return bookmarkRepository.save(bookmark);
	}
	
	@Override
	public void deleteById(Long id) {
		bookmarkRepository.deleteById(id);
	}
}
