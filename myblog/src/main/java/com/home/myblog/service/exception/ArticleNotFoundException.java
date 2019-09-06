package com.home.myblog.service.exception;

public class ArticleNotFoundException extends ServiceException {

	private static final long serialVersionUID = -3379245446960668421L;

	public ArticleNotFoundException() {
		super();
	}

	public ArticleNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ArticleNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ArticleNotFoundException(String message) {
		super(message);
	}

	public ArticleNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
