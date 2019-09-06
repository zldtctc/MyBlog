package com.home.myblog.service.exception;

/**
 * 注册时用户名重复时抛出的异常
 * 
 * @author Thinkpad
 *
 */
public class UsernameConflictException extends ServiceException {

	private static final long serialVersionUID = -2169028333744607907L;

	public UsernameConflictException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsernameConflictException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UsernameConflictException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UsernameConflictException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UsernameConflictException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
