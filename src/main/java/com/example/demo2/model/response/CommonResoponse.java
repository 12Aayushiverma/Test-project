package com.example.demo2.model.response;

public class CommonResoponse {

	private Object data;
	
	private String message;
	
	private int statusCode;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String toString() {
		return "CommonResoponse [data=" + data + ", message=" + message + ", statusCode=" + statusCode + "]";
	}

	
	
}
