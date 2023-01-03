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
import com.example.demo2.model.payload.OtpPayload;
import com.example.demo2.model.response.CommonResoponse;
import com.example.demo2.service.UtilityService;
import com.example.demo2.utils.Constants;
import com.example.demo2.utils.Messages;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtilityControllerTest {

	@MockBean
	private UtilityService utilityService;

	@Autowired
	private UtilityController utilityController;

	//@Test
	public void sendOtpTest() {

		CommonResoponse cmn = new CommonResoponse();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.OTP_TXN_ID_KEY, "112244");
		OtpPayload otpPayload = new OtpPayload();
		otpPayload.setEmail("av9028002gmail.com");
		otpPayload.setId("9179176749");
		otpPayload.setType(Constants.MOBILE_AUTHENTICATION_USECASE);
		
		cmn.setData(map);
		cmn.setMessage(Messages.OTP_SUCCESS_MSG_TEMPLATE.replace("{0}", "XX" + otpPayload.getId().substring(2, 8) + "XX"));
		cmn.setStatusCode(Constants.SUCCESS_CD);
		
		Mockito.when(utilityService.sendOtp(otpPayload)).thenReturn(cmn);
		ResponseEntity<Object> responseEntity = utilityController.sendOtp(otpPayload);
		CommonResoponse result = (CommonResoponse) responseEntity.getBody();
		Assert.assertEquals(cmn.getData(), result.getData());
		Assert.assertEquals(Constants.SUCCESS_CD, result.getStatusCode());
	}
	
	@Test
	public void validateOtpTest() {
		
		CommonResoponse cmn = new CommonResoponse();
		cmn.setMessage(Messages.OTP_SUCCESS_MSG);
		cmn.setStatusCode(Constants.OTP_SUCCESS_CD);
		
		OtpPayload otpPayload= new OtpPayload();
		otpPayload.setOtp("4565");
		otpPayload.setOtpTxnId("123546");
		otpPayload.setType(Constants.MOBILE_AUTHENTICATION_USECASE);
		
		Mockito.when(utilityService.validateOtp(otpPayload)).thenReturn(cmn);
		ResponseEntity<Object> responseEntity = utilityController.validateOtp(otpPayload);
		CommonResoponse result = (CommonResoponse) responseEntity.getBody();
		Assert.assertEquals(Messages.OTP_SUCCESS_MSG, result.getMessage());
		Assert.assertEquals(Constants.OTP_SUCCESS_CD, result.getStatusCode());
		
	}
	
	
	

}
