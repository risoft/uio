package com.uiobjects.uio.exceptions;

public class UioException extends RuntimeException {

	public UioException() {
		super();
	}

	public UioException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UioException(String message, Throwable cause) {
		super(message, cause);
	}

	public UioException(String message) {
		super(message);
	}

	public UioException(Throwable cause) {
		super(cause);
	}
	

}
