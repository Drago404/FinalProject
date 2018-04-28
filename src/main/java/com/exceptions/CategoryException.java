package com.exceptions;

public class CategoryException extends Exception{

	private static final long serialVersionUID = 4339036554271272864L;

	public CategoryException() {
	}

	public CategoryException(String message) {
		super(message);
	}

	public CategoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public CategoryException(Throwable cause) {
		super(cause);
	}

	public CategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
