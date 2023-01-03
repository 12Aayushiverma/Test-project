package com.example.demo2.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo2.model.response.CommonResoponse;
import com.example.demo2.service.CaptchaService;
import com.example.demo2.utils.Constants;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CaptchaControllerTest {

	
	 @MockBean
	   private CaptchaService captchaService;
	   
	   @Autowired
	   private CaptchaController captchaController;
	   
	   @Test
	   public void generateCaptchaTest() {
		   CommonResoponse cmn = new CommonResoponse();
		   Map<String, Object> captchaDtls = new HashMap<String, Object>();
		   captchaDtls.put(Constants.CAPTCHA_CODE_KEY, "ahfg");
		   captchaDtls.put(Constants.CAPTCHA_ID_KEY, 1);		   
		   cmn.setData(captchaDtls);
		  Mockito.when(captchaService.generateCaptcha()).thenReturn(cmn);
		  ResponseEntity<Object> cmnResponseEntity = captchaController.generateCaptcha();
		  Assert.assertEquals(cmn,cmnResponseEntity);
	   }
}
