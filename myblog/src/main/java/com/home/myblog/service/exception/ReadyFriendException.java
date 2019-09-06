package com.home.myblog.service.exception;

public class ReadyFriendException extends ServiceException {

	private static final long serialVersionUID = -3355177611154767489L;

	public ReadyFriendException() {
		super();
	}

	public ReadyFriendException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ReadyFriendException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReadyFriendException(String message) {
		super(message);
	}

	public ReadyFriendException(Throwable cause) {
		super(cause);
	}
	
}
