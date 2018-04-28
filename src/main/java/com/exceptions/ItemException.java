package com.exceptions;

public class ItemException extends Exception {

	private static final long serialVersionUID = -8383693492510009674L;

	public ItemException() {
	}

	public ItemException(String message) {
		super(message);
	}

	public ItemException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItemException(Throwable cause) {
		super(cause);
	}

	public ItemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
