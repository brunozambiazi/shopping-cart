package com.brunozambiazi.shopping.cart.exception;

public class InvalidProductException extends RuntimeException {

	private static final long serialVersionUID = -1509385281440848444L;

	public InvalidProductException(String message) {
		super(message);
	}

}
