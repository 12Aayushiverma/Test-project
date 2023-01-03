package com.example.demo2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo2.entities.UserMst;
import com.example.demo2.model.payload.UserPayload;
import com.example.demo2.model.response.CommonResoponse;
import com.example.demo2.service.UserService;
import com.example.demo2.utils.Constants;
import com.example.demo2.utils.Messages;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

	@Mock
	private UserService userService;

	@Autowired
	private UserController userController;

	// @Test
	@Order(2)
	public void addUserTest() {
		CommonResoponse cmn = new CommonResoponse();
		cmn.setMessage(Messages.SUCCESS_MSG);
		cmn.setStatusCode(Constants.SUCCESS_CD);

		UserPayload userPayload = new UserPayload();
		userPayload.setName("Aayushi");
		userPayload.setAge(22);
		userPayload.setFirstName("Aayushi");
		userPayload.setLastName("verma");
		userPayload.setEmail("av902800@gmail.com");
		userPayload.setMobileNumber("9179176749");
		userPayload.setGender('F');
		userPayload.setHeight(5.0);
		userPayload.setWeight(45);

		Mockito.when(userService.addUser(userPayload)).thenReturn(cmn);
		ResponseEntity<Object> responseEntity = userController.addUser(userPayload);
		CommonResoponse result = (CommonResoponse) responseEntity.getBody();
		Assert.assertEquals(Constants.SUCCESS_CD, result.getStatusCode());

	}

	// @Test
	@Order(3)
	public void getUser() {
		CommonResoponse cmn = new CommonResoponse();
		UserMst user = new UserMst();

		List<UserMst> list = new ArrayList<>();
		list.add(new UserMst(1, "Aayushi", "Aayushi", "Verma", "9179176749", "av902800@gamil.com", 5.0, 45, 'F', 22,
				"XuLSUmm", "admin"));
		cmn.setData(list);
		cmn.setMessage(Messages.SUCCESS_MSG);
		cmn.setStatusCode(Constants.SUCCESS_CD);
		int pageNo = 1;

		Mockito.when(userService.getUsers(pageNo)).thenReturn(cmn);
		ResponseEntity<Object> responseEntity = userController.getUsers(pageNo);
		CommonResoponse result = (CommonResoponse) responseEntity.getBody();
		List<UserMst> resultList = new ArrayList<>();
		resultList = (List) result.getData();
		Assert.assertEquals(list.size(), resultList.size());
		Assert.assertEquals(list.get(0), resultList.get(0));
		Assert.assertEquals(Messages.SUCCESS_MSG, result.getMessage());
	}

	// @Test
	@Order(1)
	public void updateUser() {

		CommonResoponse cmn = new CommonResoponse();

		UserPayload userPayload = new UserPayload();
		userPayload.setName("Aayushi");
		userPayload.setAge(22);
		userPayload.setFirstName("Aayushi");
		userPayload.setLastName("verma");
		userPayload.setEmail("av902800@gmail.com");
		userPayload.setMobileNumber("9179176749");
		userPayload.setGender('F');
		userPayload.setHeight(5.0);
		userPayload.setWeight(45);

		cmn.setData(userPayload);
		cmn.setMessage(Messages.SUCCESS_MSG);
		cmn.setStatusCode(Constants.SUCCESS_CD);

		Mockito.when(userService.updateUser(userPayload)).thenReturn(cmn);
		ResponseEntity<Object> responseEntity = userController.updateUser(userPayload);
		CommonResoponse result = (CommonResoponse) responseEntity.getBody();
		Assert.assertEquals(Messages.SUCCESS_MSG, result.getMessage());
		Assert.assertEquals(Constants.SUCCESS_CD, result.getStatusCode());
	}

	// @Test
	@Order(4)
	public void searchUsersTest() {

		CommonResoponse cmn = new CommonResoponse();

		String search = "av902800@gmail.com";
		List<UserMst> list = new ArrayList<>();
		list.add(new UserMst(1, "Aayushi", "Aayushi", "Verma", "9179176749", "av902800@gamil.com", 5.0, 45, 'F', 22,
				"XuLSUmm", "admin"));
		cmn.setData(list);
		cmn.setMessage(Messages.SUCCESS_MSG);
		cmn.setStatusCode(Constants.SUCCESS_CD);

		Mockito.when(userService.searchUser(search)).thenReturn(cmn);
		ResponseEntity<Object> responseEntity = userController.searchUsers(search);
		CommonResoponse result = (CommonResoponse) responseEntity.getBody();
		Assert.assertEquals(cmn.getData(), result.getData());
		Assert.assertEquals(Messages.SUCCESS_MSG, result.getMessage());
		Assert.assertEquals(Constants.SUCCESS_CD, result.getStatusCode());
	}

	@Test
	@Order(5)
	public void profileUploadTest() throws IOException {

		CommonResoponse cmn = new CommonResoponse();

		cmn.setData("src\\main\\resources\\static\\image\\1.jpg");
		cmn.setMessage(Messages.PROFILE_UPLOAD_SUCCESS_MSG);
		cmn.setStatusCode(Constants.SUCCESS_CD);
		String userId = "av902800@gamil.com";

		File file = new File("src\\main\\resources\\static\\image\\1.jpg");
		FileInputStream input = new FileInputStream(file);

		MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "image/jpeg",
				IOUtils.toByteArray(input));

		Mockito.when(userService.uploadProfile(multipartFile, userId)).thenReturn(cmn);
		ResponseEntity<Object> responseEntity = (ResponseEntity<Object>) userController.profileUpload(multipartFile,
				userId);
		CommonResoponse result = (CommonResoponse) responseEntity.getBody();
		Assert.assertEquals(cmn.getData(), result.getData());
		Assert.assertEquals(Messages.PROFILE_UPLOAD_SUCCESS_MSG, result.getMessage());
		Assert.assertEquals(Constants.SUCCESS_CD, result.getStatusCode());

	}

}
