package com.librarymanagement.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@NoArgsConstructor
public class BookNamePresentException extends RuntimeException {
	private static final Logger logger = LoggerFactory.getLogger(BookNamePresentException.class);
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;

	/**
	 * Constructs an EmptyFieldException with the specified error code and error
	 * message.
	 *
	 * @param errorCode    The error code associated with the exception.
	 * @param errorMessage The error message associated with the exception.
	 */
	public BookNamePresentException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;

		logger.error("Book name present exception occurred - ErrorCode: {}, ErrorMessage: {}", errorCode, errorMessage);
	}

}
