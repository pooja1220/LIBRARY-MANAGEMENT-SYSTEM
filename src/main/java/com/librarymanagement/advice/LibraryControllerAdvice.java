package com.librarymanagement.advice;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.librarymanagement.controller.*;
import com.librarymanagement.exceptions.*;

/**
 * This class serves as a global exception handler for the library application.
 * It handles various exceptions and generates appropriate responses for them.
 */
@ControllerAdvice
public class LibraryControllerAdvice extends ResponseEntityExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

	/**
	 * Handles the EmptyFieldException by returning an appropriate error response.
	 *
	 * @param emptyFieldException The exception to be handled.
	 * @return ResponseEntity containing the error message and HTTP status code.
	 */
	@ExceptionHandler(EmptyFieldException.class)
	public ResponseEntity<String> handleEmptyInputException(EmptyFieldException emptyFieldException) {
		logger.error("An empty field exception occurred: {}", emptyFieldException.getMessage());
		return new ResponseEntity<String>("Input field is empty,Please look into it", HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles the NoSuchElementException by returning an appropriate error
	 * response.
	 *
	 * @param noSuchElementException The exception to be handled.
	 * @return ResponseEntity containing the error message and HTTP status code.
	 */
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
		logger.error("A no such element exception occurred: {}", noSuchElementException.getMessage());
		return new ResponseEntity<String>("No value is present in DB,Please change your request", HttpStatus.NOT_FOUND);
	}

	/**
	 * Handles the HttpRequestMethodNotSupportedException by returning an
	 * appropriate error response.
	 *
	 * @param ex      The exception to be handled.
	 * @param headers The HTTP headers.
	 * @param status  The HTTP status code.
	 * @param request The WebRequest.
	 * @return ResponseEntity containing the error message and HTTP status code.F
	 */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		logger.error("HTTP request method not supported: {}", ex.getMessage());
		return new ResponseEntity<Object>("Please,change your HTTP method type", HttpStatus.NOT_FOUND);
	}

}
