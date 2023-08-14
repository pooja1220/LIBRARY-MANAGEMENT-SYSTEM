package com.librarymanagement.service;

import java.util.List;
import java.util.Set;

import com.librarymanagement.entity.Book;
import com.librarymanagement.entity.Category;

/**
 * Service interface for managing operations related to the library management
 * system.
 */
public interface LibraryService {

	/**
	 * Adds a new category to the system.
	 *
	 * @param category The category to be added.
	 * @return The added category.
	 */
	public Category addNewCategory(Category category);

	/**
	 * Adds a new book to the system.
	 *
	 * @param book The book to be added.
	 * @return The added book.
	 */
	public Book addNewBook(Book book);

	/**
	 * Updates an existing book in the system.
	 *
	 * @param bookId      The ID of the book to be updated.
	 * @param updatedBook The updated book details.
	 * @return The updated book.
	 */
	public Book updateBook(Long bookId, Book updatedBook);

	/**
	 * Retrieves all available genres (categories) from the system.
	 *
	 * @return List of all available genres (categories).
	 */
	public List<Category> getAllGenres();

	/**
	 * Retrieves all books belonging to a specific genre (category).
	 *
	 * @param categoryId The ID of the category (genre).
	 * @return Set of books belonging to the specified genre.
	 */
	public Set<Book> getBooksByGenre(Long categoryId);

	/**
	 * Deletes a book from the system.
	 *
	 * @param bookId The ID of the book to be deleted.
	 */
	public void deleteBook(Long bookId);

	/**
	 * Retrieves a list of all books in the system.
	 *
	 * @return List of all books.
	 */
	public List<Book> findAllBooks();

	/**
	 * Searches for books by their name.
	 *
	 * @param name The name of the book to search for.
	 * @return List of books matching the search criteria.
	 */
	public List<Book> findBookByName(String name);

	/**
	 * Updates an existing category in the system with the provided category
	 * details.
	 *
	 * @param categoryId The ID of the category to be updated.
	 * @param category   The updated category details.
	 * @return The updated category.
	 * @throws NotFoundException if the specified category ID is not found.
	 */
	public Category updateCategory(Long categoryId, Category category);

	/**
	 * Deletes a category from the system.
	 *
	 * @param categoryId The ID of the category to be deleted.
	 */
	public void deleteCategory(Long categoryId);

}
