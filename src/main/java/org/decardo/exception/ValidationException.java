package org.decardo.exception;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2024/04/01
 */
public class ValidationException extends RuntimeException {
	public ValidationException(String message) {
		super(message);
	}
}
