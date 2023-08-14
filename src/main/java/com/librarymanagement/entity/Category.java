package com.librarymanagement.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Represents a Category entity in the library management system.
 */
@Entity
@Table(name = "categories")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;

	@Column(name = "category_name", length = 50, nullable = false, unique = true)
	private String categoryName;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "categories")
	@JsonIgnoreProperties("categories")
	private Set<Book> setOfBooks = new HashSet<>();

	// Getter Methods

	public Long getCategoryId() {
		return categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public Set<Book> getSetOfBooks() {
		return setOfBooks;
	}

	// Setter Methods

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setSetOfBooks(Set<Book> setOfBooks) {
		this.setOfBooks = setOfBooks;
	}

	/**
	 * Create a new Category instance with provided values.
	 *
	 * @param categoryId   The category's ID.
	 * @param categoryName The name of the category.
	 * @param setOfBooks   The set of books belonging to this category.
	 */
	public Category(Long categoryId, String categoryName, Set<Book> setOfBooks) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.setOfBooks = setOfBooks;
	}

	/**
	 * Create a new empty Category instance.
	 */
	public Category() {
		super();
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", setOfBooks=" + setOfBooks
				+ "]";
	}

}
