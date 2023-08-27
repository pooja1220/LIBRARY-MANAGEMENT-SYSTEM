package com.librarymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.librarymanagement.entity.Book;

/**
 * Repository interface for managing Book entities in the library management
 * system.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

	/**
	 * Retrieve a list of books with a specific name.
	 *
	 * @param name The name of the book to search for.
	 * @return A list of books with the given name.
	 */
	List<Book> findBookByName(String name);


//	List<Book> findByName(String name);

}
