package com.example.demo2.service;

public interface CaptchaService<T> {

	
	public T generateCaptcha();
	
	public T validateCaptcha(int captchaId, String captchaCode);
	
}
