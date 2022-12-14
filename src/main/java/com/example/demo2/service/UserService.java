package com.example.demo2.service;

import com.example.demo2.model.payload.OtpPayload;
import com.example.demo2.model.payload.UserPayload;

public interface UserService<T> {

	public T addUser(UserPayload user);

	public T updateUser(UserPayload user);

	public T getUsers();
	
	public T searchUser(String search);
	
	

}
