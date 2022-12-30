package com.example.demo2.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CAPTCHA_DTLS")
public class CaptchaDtls {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int captchaId;
	
	private String captchaCode;

	public int getCaptchaId() {
		return captchaId;
	}

	public void setCaptchaId(int captchaId) {
		this.captchaId = captchaId;
	}

	public String getCaptchaCode() {
		return captchaCode;
	}

	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}

	public CaptchaDtls(int captchaId, String captchaCode) {
		super();
		this.captchaId = captchaId;
		this.captchaCode = captchaCode;
	}

	public CaptchaDtls(String captchaCode) {
		super();
		this.captchaCode = captchaCode;
	}

	public CaptchaDtls() {
		super();
	}
	
	
	

}
