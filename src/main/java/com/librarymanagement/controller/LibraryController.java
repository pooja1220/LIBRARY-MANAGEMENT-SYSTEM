package com.librarymanagement.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.librarymanagement.entity.Book;
import com.librarymanagement.entity.Category;
import com.librarymanagement.service.impl.LibraryServiceImpl;

/**
 * Controller class for managing operations related to the library management
 * system.
 */
@RestController
@RequestMapping("/library")
public class LibraryController {

	private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

	@Autowired
	private LibraryServiceImpl libraryService;

	/**
	 * Constructor for injecting the LibraryService dependency.
	 *
	 * @param libraryService The LibraryService instance.
	 */
	public LibraryController(LibraryServiceImpl libraryService) {
		super();
		this.libraryService = libraryService;
	}

	/**
	 * Adds a new category to the system.
	 *
	 * @param category The category to be added.
	 * @return The added category with a HTTP status of 201 (Created).
	 */
	@PostMapping("/category/addcategory")
	public ResponseEntity<Category> addNewCategory(@RequestBody Category category) {
		logger.info("Adding a new category: " + category.getCategoryName());
		Category addedCategory = libraryService.addNewCategory(category);
		logger.info("Category added: {}", addedCategory.getCategoryName());
		return new ResponseEntity<Category>(addedCategory, HttpStatus.CREATED);
	}

	/**
	 * Adds a new book to the system.
	 *
	 * @param book The book to be added.
	 * @return The added book with a HTTP status of 201 (Created).
	 */
	@PostMapping("/books/addbook")
	public ResponseEntity<Book> addNewBook(@RequestBody Book book) {
		logger.info("Adding a new book: " + book.getName());
		Book addedBook = libraryService.addNewBook(book);
		logger.info("Book added: {}", addedBook.getName());
		return new ResponseEntity<Book>(addedBook, HttpStatus.CREATED);
	}

	/**
	 * Updates the details of a book in the system.
	 *
	 * @param bookId      The ID of the book to be updated.
	 * @param updatedBook The updated book details.
	 * @return The updated book with a HTTP status of 200 (OK).
	 */
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book> updateBookDetails(@PathVariable Long bookId, @RequestBody Book updatedBook) {
		logger.info("Updating book with ID: " + bookId);
		Book updated = libraryService.updateBook(bookId, updatedBook);
		logger.info("Book updated: {}", updated.getName());
		return new ResponseEntity<Book>(updated, HttpStatus.OK);
	}

	/**
	 * Retrieves a list of all available genres (categories) in the system.
	 *
	 * @return A list of genres with a HTTP status of 200 (OK).
	 */
	@GetMapping("/category/genres")
	public ResponseEntity<List<Category>> getAllGenres() {
		logger.info("Getting all genres");
		List<Category> genres = libraryService.getAllGenres();
		logger.info("Total categories retrieved: {}", genres.size());
		return new ResponseEntity<List<Category>>(genres, HttpStatus.OK);
	}

	/**
	 * Retrieves a set of books belonging to a specific genre (category) by its ID.
	 *
	 * @param categoryId The ID of the genre (category).
	 * @return A set of books with a HTTP status of 200 (OK).
	 */
	@GetMapping("/books/{categoryId}")
	public ResponseEntity<Set<Book>> getBooksByGenre(@PathVariable Long categoryId) {
		logger.info("Getting books for category with ID: " + categoryId);
		Set<Book> books = libraryService.getBooksByGenre(categoryId);
		logger.info("Total books retrieved for category with ID '{}': {}", categoryId, books.size());
		return new ResponseEntity<Set<Book>>(books, HttpStatus.OK);
	}

	/**
	 * Deletes a book from the system by its ID.
	 *
	 * @param bookId The ID of the book to be deleted.
	 * @return A response entity with a HTTP status of 202 (Accepted) indicating
	 *         successful deletion.
	 */
	@DeleteMapping("/deletebook/{bookid}")
	public ResponseEntity<Void> deleteBookById(@PathVariable("bookid") Long bookId) {
		logger.info("Deleting book with ID: " + bookId);
		libraryService.deleteBook(bookId);
		logger.info("Book deleted with ID: {}", bookId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	/**
	 * Retrieves a list of all books in the system.
	 *
	 * @return A list of books with a HTTP status of 200 (OK).
	 */
	@GetMapping("/getallbook")
	public ResponseEntity<List<Book>> getAllBooks() {
		logger.info("Getting all books");
		List<Book> listOfBooks = libraryService.findAllBooks();
		logger.info("Total books retrieved: {}", listOfBooks.size());
		return new ResponseEntity<List<Book>>(listOfBooks, HttpStatus.OK);
	}

	/**
	 * Retrieves a list of books by their name.
	 *
	 * @param name The name of the book to search for.
	 * @return A list of matching books with a HTTP status of 200 (OK).
	 */
	@GetMapping("/getbookbyname/{name}")
	public ResponseEntity<List<Book>> getBookByName(@PathVariable("name") String name) {
		logger.info("Searching for books with name: " + name);
		List<Book> listOfBooks = libraryService.findBookByName(name);
		logger.info("Total books retrieved with name '{}': {}", name, listOfBooks.size());
		return new ResponseEntity<List<Book>>(listOfBooks, HttpStatus.OK);
	}

	/**
	 * Updates the details of a category in the system.
	 *
	 * @param categoryId     The ID of the category to be updated.
	 * @param updateCategory The updated category details.
	 * @return The updated category with a HTTP status of 201 (Created).
	 */
	@PutMapping("/updatecategory/{categoryId}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId,
			@RequestBody Category updateCategory) {
		logger.info("Updating category with ID: " + categoryId);
		Category updatedCategory = libraryService.updateCategory(categoryId, updateCategory);
		logger.info("Category updated: {}", updatedCategory.getCategoryName());
		return new ResponseEntity<Category>(updatedCategory, HttpStatus.CREATED);
	}

	/**
	 * Deletes a category from the system by its ID.
	 *
	 * @param categoryId The ID of the category to be deleted.
	 * @return A response entity with a HTTP status of 202 (Accepted) indicating
	 *         successful deletion.
	 */
	@DeleteMapping("/deletecategory/{categoryid}")
	public ResponseEntity<Void> deleteCategoryById(@PathVariable("categoryid") Long categoryId) {
		logger.info("Deleting category with ID: " + categoryId);
		libraryService.deleteCategory(categoryId);
		logger.info("Category deleted with ID: {}", categoryId);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}
