package com.example.demo2.utils;

public class Constants {

	public static int SUCCESS_CD = 200;

	public static int ERROR_CD = 500;

	public static int NULL_REQUEST = -1001;

	public static String NAME_REGEX = "^[a-zA-Z]*$";

	public static String MOBILE_NO_REGEX = "(0|91)?[6-9][0-9]{9}";
	
	public static String EMAIL_REGEX ="^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

	public static int INVALID_MOBILE_NO_CD = -1061;
	
	public static int INVALID_EMAIL_CD = -1062;
	
	public static int USER_ALREADY_EXIST = -1063;
	
	public static String OTP_TXN_ID_KEY = "otpTxnId" ;
}
