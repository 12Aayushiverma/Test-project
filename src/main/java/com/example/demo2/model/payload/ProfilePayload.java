package com.example.demo2.model.payload;

public class ProfilePayload {

	
	private String path;
	
	private String userId;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ProfilePayload(String path, String userId) {
		super();
		this.path = path;
		this.userId = userId;
	}

	public ProfilePayload() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
