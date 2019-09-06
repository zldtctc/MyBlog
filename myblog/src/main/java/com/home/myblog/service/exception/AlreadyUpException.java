package com.home.myblog.service.exception;

public class AlreadyUpException extends ServiceException {

	private static final long serialVersionUID = 3255048194568617798L;

	public AlreadyUpException() {
		super();
	}

	public AlreadyUpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AlreadyUpException(String message, Throwable cause) {
		super(message, cause);
	}

	public AlreadyUpException(String message) {
		super(message);
	}

	public AlreadyUpException(Throwable cause) {
		super(cause);
	}
	
}
