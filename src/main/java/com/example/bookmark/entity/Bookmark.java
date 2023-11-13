package com.example.bookmark.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookmarks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String url;
	
	@Column
	private String description;
	
	@ManyToMany
	@JoinTable(
			name = "bookmark_categories",
			joinColumns = @JoinColumn(name = "bookmark_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id")
	)
	private List<Category> categories;
	
	@Transient
	private List<Long> categoryIds;
	
	public List<Long> getCategoryIds(){
		return categoryIds;
	}
	
	public void setCategoryIds(List<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}
}
