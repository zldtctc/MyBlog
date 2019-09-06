package com.home.myblog.service.exception;

public class UploadException extends ServiceException {

	private static final long serialVersionUID = 5044907753400768177L;

	public UploadException() {
		super();
	}

	public UploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UploadException(String message, Throwable cause) {
		super(message, cause);
	}

	public UploadException(String message) {
		super(message);
	}

	public UploadException(Throwable cause) {
		super(cause);
	}
	
}
