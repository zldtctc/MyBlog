package com.home.myblog.service.exception;

public class ApplyNotFoundException extends ServiceException {

	private static final long serialVersionUID = -4284684457933765178L;

	public ApplyNotFoundException() {
		super();
	}

	public ApplyNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ApplyNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplyNotFoundException(String message) {
		super(message);
	}

	public ApplyNotFoundException(Throwable cause) {
		super(cause);
	}

}
