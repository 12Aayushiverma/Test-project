package com.example.demo2.model.dto;

public class SendSmsDto {

	
	private String mobileNumber;
    private Integer otp;
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Integer getOtp() {
		return otp;
	}
	public void setOtp(Integer otp) {
		this.otp = otp;
	}
	public SendSmsDto(String mobileNumber, Integer otp) {
		super();
		this.mobileNumber = mobileNumber;
		this.otp = otp;
	}
	
}
