package com.example.demo2.controller;

import java.util.HashMap;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo2.model.payload.LoginPayload;
import com.example.demo2.model.response.CommonResoponse;
import com.example.demo2.service.LoginService;
import com.example.demo2.utils.Constants;
import com.example.demo2.utils.Messages;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginControllerTest {

	@MockBean
	private LoginService loginService;

	@Autowired
	private loginController loginController;

	@Test
	public void loginTest() {

		CommonResoponse cmn = new CommonResoponse();
		HashMap<String, String> res = new HashMap<>();
		res.put(Constants.JWT_KEY,
				"eyJhbGciOiJIUzUxMiJ9.eyJNb2JpbGVObyI6IjkxNzkxNzY3NDkiLCJzdWIiOiJhdmM5MDI4MDBAZ21haWwuY29tIiwiZXhwIjoyNDkyNzc2Nzc4LCJpYXQiOjE2NzI2NDU5NjJ9...\r\n");
		cmn.setData(res);
		cmn.setMessage(Messages.SUCCESS_MSG);
		cmn.setStatusCode(Constants.SUCCESS_CD);
		
		LoginPayload loginPayload = new LoginPayload();
		loginPayload.setUsername("av902800@gmail.com");
		loginPayload.setPassword("XuLSUmm");
		loginPayload.setCaptchaCode("FsA3");
		loginPayload.setCaptchaId(2);

		Mockito.when(loginService.login(loginPayload)).thenReturn(cmn);
		ResponseEntity<Object> responseEntity = loginController.login(loginPayload);
		CommonResoponse result = (CommonResoponse) responseEntity.getBody();
		Assert.assertEquals(cmn.getData(), result.getData());
		Assert.assertEquals(Constants.SUCCESS_CD, result.getStatusCode());

	}

}
