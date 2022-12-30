package com.example.demo2.service;

public interface CaptchaService<T> {

	
	public T generateCaptcha();
	
	public T validateCaptcha(Integer captchaId, String captchaCode);
	
}
