package com.example.demo2.model.payload;

public class OtpPayload {

	
	
	private String id;
    
    private String otp;
    
    private String otpTxnId;
        
    private String mobileNo;
    
    private String email;
    
    private String type;
    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getOtpTxnId() {
		return otpTxnId;
	}

	public void setOtpTxnId(String otpTxnId) {
		this.otpTxnId = otpTxnId;
	}


	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String status;
    
    

}
