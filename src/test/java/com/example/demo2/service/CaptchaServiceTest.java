package com.example.demo2.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo2.controller.CaptchaController;
import com.example.demo2.entities.CaptchaDtls;
import com.example.demo2.model.response.CommonResoponse;
import com.example.demo2.repositories.CaptchaRepository;
import com.example.demo2.utils.Constants;
import com.example.demo2.utils.Messages;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CaptchaServiceTest {

	
	@MockBean
	 CaptchaRepository captchaRepository;

	@Autowired
	private CaptchaService captchaService;
	
	@Test
	public void generateCaptchaTest() {

		CaptchaDtls captchaDtls = new CaptchaDtls();
		String captchaCode = "Asu4";
		int captchaId = 2;
		captchaDtls.setCaptchaCode(captchaCode);
		captchaDtls.setCaptchaId(captchaId);
		CommonResoponse cmn = new CommonResoponse();
		

		Mockito.when(captchaRepository.save(new CaptchaDtls(captchaCode))).thenReturn(captchaDtls);
		cmn =(CommonResoponse) this.captchaService.generateCaptcha();
        Map<String, Object> result = (Map)cmn.getData();
        Assert.assertEquals(captchaDtls.getCaptchaId(),result.get(Constants.CAPTCHA_ID_KEY) );
        Assert.assertEquals(captchaDtls.getCaptchaCode(), result.get(Constants.CAPTCHA_CODE_KEY));
	}
}
