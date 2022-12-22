package com.example.demo2.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

import org.springframework.web.multipart.MultipartFile;

public class ProfileUploadHelper {

public final String UPLOAD_DIR = "src\\main\\resources\\static\\image";
	
	
	public String uploadFile(MultipartFile multipart) {
		
		
		String str= null;
		
		try {
			Random random= new Random();
			String n = String.valueOf(random.nextInt(99)) ;
			String s1 = multipart.getOriginalFilename();
			String[] arr = s1.split("[.]");
			String s2 = arr[0]+n+"."+arr[1];
			
			
			Files.copy(multipart.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+s2), StandardCopyOption.REPLACE_EXISTING);
			
			 str = (Paths.get(UPLOAD_DIR+File.separator+s2).toString());
			
		} catch (Exception e) {
             e.printStackTrace();
		}
		return str;
		
		
	}


	
}
