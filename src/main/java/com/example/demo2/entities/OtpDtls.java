package com.example.demo2.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "OTP_DTLS")
public class OtpDtls {

	
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Id
		private Integer id;
	    
	    @Column(name= "OTP", length = 6)
	    private String otp;
	    
	    
	    @Column(name = "OTP_TXN_ID",length = 8, unique = true)
	    private String otpTxnId;
	    
	    @Column(name = "EXPIRY_TIME")
	    private Date expiryTime;
	    
	    @Column(name = " MOBILE_NO")
	    private String mobileNo;
	    
	    @Column(name ="EMAIL")
	    private String email;
	    
	    @Column(name = "STATUS")
	    private String status;
	    
	    @Column(name = "TYPE")
	    private String type;
	    
	    

		public Date getExpiryTime() {
			return expiryTime;
		}

		public void setExpiryTime(Date expiryTime) {
			this.expiryTime = expiryTime;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public OtpDtls() {
			super();
			// TODO Auto-generated constructor stub
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

		public Date getGeneratedtime() {
			return expiryTime;
		}

		public void setGeneratedtime(Date generatedtime) {
			this.expiryTime = generatedtime;
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

		public OtpDtls(int id, String otp, String otpTxnId, Date generatedtime, String mobileNo, String email,
				String status) {
			super();
			this.id = id;
			this.otp = otp;
			this.otpTxnId = otpTxnId;
			this.expiryTime = generatedtime;
			this.mobileNo = mobileNo;
			this.email = email;
			this.status = status;
		}
	    
	    
	    
	 
	   
}
