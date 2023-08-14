package com.librarymanagement.service.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.librarymanagement.entity.Book;
import com.librarymanagement.entity.Category;
import com.librarymanagement.exceptions.*;
import com.librarymanagement.repository.BookRepository;
import com.librarymanagement.repository.CategoryRepository;
import com.librarymanagement.service.LibraryService;

/**
 * Service implementation class for managing library operations.
 */

@Service
public class LibraryServiceImpl implements LibraryService {
	private static final Logger logger = LoggerFactory.getLogger(LibraryServiceImpl.class.getName());

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * Constructs a LibraryServiceImpl with the provided repositories.
	 *
	 * @param bookRepository     The repository for managing Book entities.
	 * @param categoryRepository The repository for managing Category entities.
	 */
	public LibraryServiceImpl(BookRepository bookRepository, CategoryRepository categoryRepository) {
		super();
		this.bookRepository = bookRepository;
		this.categoryRepository = categoryRepository;
	}

	/**
	 * Adds a new category to the system.
	 *
	 * @param category The category to be added.
	 * @return The added category.
	 */
	@Transactional
	public Category addNewCategory(Category category) {
		logger.info("Adding new category: " + category.getCategoryName());
		return categoryRepository.save(category);
	}

	/**
	 * Adds a new book to the system.
	 *
	 * @param book The book to be added.
	 * @return The added book.
	 * @throws NotFoundException if the associated category is not found.
	 */
	@Transactional
	public Book addNewBook(Book book) {
		logger.info("Creating a new book: {}", book.getName());
		// Logic to associate the book with an existing category
		Category category = categoryRepository.findById(book.getCategoryId())
				.orElseThrow(() -> new NotFoundException("Category not found "));
		book.setCategories(category);
		Book addedBook = bookRepository.save(book);
		logger.info("Added new book: " + addedBook.getName());
		return addedBook;
	}

	/**
	 * Updates an existing book in the system.
	 *
	 * @param bookId      The ID of the book to be updated.
	 * @param updatedBook The updated book details.
	 * @return The updated book.
	 * @throws NotFoundException if the specified book ID is not found.
	 */
	@Transactional
	public Book updateBook(Long bookId, Book updatedBook) {
		logger.info("Updating book with ID: {}", updatedBook.getBookId());
		Book existingBook = bookRepository.findById(bookId)
				.orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", bookId)));
		// Update properties of the existing book with properties from updatedBook
		existingBook.setName(updatedBook.getName());
		existingBook.setBookDescription(updatedBook.getBookDescription());
		Book updated = bookRepository.save(existingBook);
		logger.info("Updated book details: " + updated);
		return updated;
	}

	/**
	 * Retrieves a list of all available categories.
	 *
	 * @return List of all categories.
	 * @throws EmptyFieldException if the list of categories is empty.
	 */
	@Transactional(readOnly = true)
	public List<Category> getAllGenres() {
		logger.info("Getting all categories");
		List<Category> listOfCategories = categoryRepository.findAll();
		if (listOfCategories.isEmpty()) {
			throw new EmptyFieldException("602", "The list is empty");
		} else {
			logger.info("Total categories retrieved: {}", listOfCategories.size());
			return listOfCategories;
		}
	}

	/**
	 * Retrieves a set of books belonging to a specific category by its ID.
	 *
	 * @param categoryId The ID of the category.
	 * @return A set of books belonging to the specified category.
	 * @throws RuntimeException if the category with the given ID is not found.
	 */
	@Transactional(readOnly = true)
	public Set<Book> getBooksByGenre(Long categoryId) {
		logger.info("Getting books for category with ID: " + categoryId);
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new RuntimeException("Category not found"));
		return category.getSetOfBooks();
	}

	/**
	 * Deletes a book from the system by its ID.
	 *
	 * @param bookId The ID of the book to be deleted.
	 * @throws NotFoundException if the specified book ID is not found.
	 */
	@Transactional
	public void deleteBook(Long bookId) {
		logger.info("Deleting book with ID: " + bookId);
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", bookId)));
		bookRepository.deleteById(book.getBookId());
		logger.info("Book deleted with ID: {}", bookId);
	}

	/**
	 * Retrieves a list of all available books in the system.
	 *
	 * @return A list of all available books.
	 * @throws EmptyFieldException if the list of books is empty.
	 */
	@Transactional(readOnly = true)
	public List<Book> findAllBooks() {
		logger.info("Getting all books");
		List<Book> listOfBooks = bookRepository.findAll();
		if (listOfBooks.isEmpty()) {
			throw new EmptyFieldException("602", "The list is empty");
		} else {
			logger.info("Total books retrieved: {}", listOfBooks.size());
			return listOfBooks;
		}
	}

	/**
	 * Searches for books with a specific name.
	 *
	 * @param name The name of the book to search for.
	 * @return A list of books with the specified name.
	 * @throws NullFieldException if the provided book name is null.
	 */
	@Transactional(readOnly = true)
	public List<Book> findBookByName(String name) {
		logger.info("Searching for books with name: " + name);
		List<Book> book = bookRepository.findBookByName(name);
		if (name == null) {
			throw new NullFieldException("601", "name of the book is null");
		} else {
			logger.info("Total books retrieved for name '{}': {}", name, book.size());
			return book;
		}
	}

	/**
	 * Updates an existing category in the system.
	 *
	 * @param categoryId      The ID of the category to be updated.
	 * @param updatedCategory The updated category details.
	 * @return The updated category.
	 * @throws NotFoundException if the specified category ID is not found.
	 */
	@Transactional
	public Category updateCategory(Long categoryId, Category updatedCategory) {
		logger.info("Updating category with ID: " + categoryId);
		Category existingCategory = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new NotFoundException(String.format("Category not found with ID %d", categoryId)));
		// Update properties of the existing Category with properties from
		// updatedCategory
		existingCategory.setCategoryName(updatedCategory.getCategoryName());
		return categoryRepository.save(existingCategory);

	}

	/**
	 * Deletes a category from the system by its ID.
	 *
	 * @param categoryId The ID of the category to be deleted.
	 * @throws NotFoundException if the specified category ID is not found.
	 */
	@Transactional
	public void deleteCategory(Long categoryId) {
		logger.info("Deleting category with ID: " + categoryId);
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new NotFoundException(String.format("Category not found with ID %d", categoryId)));
		categoryRepository.deleteById(category.getCategoryId());
		logger.info("Category deleted with ID: {}", categoryId);
	}
}