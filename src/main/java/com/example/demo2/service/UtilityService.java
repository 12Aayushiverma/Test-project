package com.example.demo2.service;

import com.example.demo2.model.payload.OtpPayload;

public interface UtilityService<T> {

	
	
	public T sendOtp(OtpPayload otpPayload);
	
	public T validateOtp(OtpPayload otpPayload);
	
	public String getMobileNoFromToken();
}
