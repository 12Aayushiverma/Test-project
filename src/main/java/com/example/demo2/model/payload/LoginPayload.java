package com.example.demo2.model.payload;

public class LoginPayload {

	
	private String username;
	
	private String password;
	
	private String captchaCode;
	
	private Integer captchaId;
	
	

	public String getCaptchaCode() {
		return captchaCode;
	}

	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}

	
	public Integer getCaptchaId() {
		return captchaId;
	}

	public void setCaptchaId(int captchaId) {
		this.captchaId = captchaId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginPayload [username=" + username + ", password=" + password + ", captchaCode=" + captchaCode
				+ ", captchaId=" + captchaId + "]";
	}

	
	
	
	
}
