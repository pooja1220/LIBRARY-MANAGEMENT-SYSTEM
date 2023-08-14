package com.librarymanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * The main class that starts the Library Management Application.
 */

@SpringBootApplication
@EnableTransactionManagement // Enable transaction management
public class LibrarymanagementApplication {
	private static final Logger logger = LoggerFactory.getLogger(LibrarymanagementApplication.class);

	/**
	 * The main method that initializes and starts the Spring Boot application.
	 *
	 * @param args The command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		logger.info("Starting LibrarymanagementApplication...");
		SpringApplication.run(LibrarymanagementApplication.class, args);
		logger.info("LibrarymanagementApplication started.");
	}
}
