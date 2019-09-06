package com.home.myblog.util;

import java.io.Serializable;


public class JsonResult<T> implements Serializable {

	private static final long serialVersionUID = 8733391754772249157L;

	private Integer state;
	private String message;
	private T data;
	
	public JsonResult() {
		super();
	}

	public JsonResult(Integer state) {
		super();
		this.state = state;
	}

	public JsonResult(Exception e) {
		super();
		this.message = e.getMessage();
	}
	
	public JsonResult(T data) {
		super();
		this.data = data;
	}
	
	public JsonResult(Integer state, T data) {
		super();
		this.state = state;
		this.data = data;
	}

	@Override
	public String toString() {
		return "JsonResult [state=" + state + ", message=" + message + ", data=" + data + "]";
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
