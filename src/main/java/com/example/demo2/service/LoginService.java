package com.example.demo2.service;

import com.example.demo2.model.payload.LoginPayload;

public interface LoginService<T> {

	public T login(LoginPayload loginPayload);
	
}
