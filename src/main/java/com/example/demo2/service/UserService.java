package com.example.demo2.service;

import org.springframework.web.multipart.MultipartFile;
import com.example.demo2.model.payload.UserPayload;

public interface UserService<T> {

	public T addUser(UserPayload user);

	public T updateUser(UserPayload user);

	public T getUsers(int pageNo);
	
	public T searchUser(String search);
	
	public T uploadProfile(MultipartFile file, String userId);
	
	public String uploadFile(MultipartFile multipart, String userId);
	
	

}
