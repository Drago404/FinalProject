package com.exceptions;

public class OrderException extends Exception {

	private static final long serialVersionUID = 4337536554271272864L;
	
	public OrderException() {
	}

	public OrderException(String message) {
		super(message);
	}

	public OrderException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderException(Throwable cause) {
		super(cause);
	}

	public OrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}