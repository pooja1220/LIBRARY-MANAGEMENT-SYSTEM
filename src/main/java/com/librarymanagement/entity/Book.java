package com.librarymanagement.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * Represents a Book entity in the library management system.
 */

@Entity
@Table(name = "books")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;

	@Column(name = "book_name",length = 100, nullable = false)
	private String name;

	@Column(name = "book_description", length = 250, nullable = false)
	private String bookDescription;

	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonIgnoreProperties("setOfBooks")
	private Category categories;

	@Transient
	private Long categoryId;

	// Getter Methods

	public Long getBookId() {
		return bookId;
	}

	public String getName() {
		return name;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public Category getCategories() {
		return categories;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	// Setter Methods

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public void setCategories(Category categories) {
		this.categories = categories;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", name=" + name + ", bookDescription=" + bookDescription + ", categoryId="
				+ categoryId + "]";
	}

	/**
	 * Create a new Book instance with provided values.
	 *
	 * @param bookId          The book's ID.
	 * @param name            The name of the book.
	 * @param bookDescription The description of the book.
	 * @param categories      The category to which the book belongs.
	 * @param categoryId      The ID of the category to which the book belongs.
	 */

	public Book(Long bookId, String name, String bookDescription, Category categories, Long categoryId) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.bookDescription = bookDescription;
		this.categories = categories;
		this.categoryId = categoryId;
	}

	/**
	 * Create a new empty Book instance.
	 */
	public Book() {
		super();
	}

}