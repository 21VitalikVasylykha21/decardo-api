package org.decardo.exception;

/**
 * @author Vitalii Vasylykha
 * @company UzhNU
 * @since 2023/12/17
 */
public class ExistObjectException extends RuntimeException {
	public ExistObjectException(Class object, String value) {
		super("The " + object.getSimpleName() + " object already exists in the value " + value);
	}

	public ExistObjectException(Class object) {
		super("The " + object.getSimpleName() + " object already exists.");
	}
}
