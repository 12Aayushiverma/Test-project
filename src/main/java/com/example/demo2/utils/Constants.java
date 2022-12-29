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
	
	public static int INVALID_OTP = -1071;
	
	public static int INVALID_EMAIL_CD = -1062;
	
	public static int USER_ALREADY_EXIST = -1063;
	
	public static String OTP_TXN_ID_KEY = "otpTxnId" ;
	
	public static int OTP_SUCCESS_CD = 1071;
	
	public static int REQUEST_FOR_OTP_CD = -1064;
	
    public static String PASSWORD_HELPER = "abchefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789@#$*&";
    
    public static String CAPTCHA_HELPER = "abchefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    
    public static int INVALID_PASSWORD = -1065;
    
    public static int USER_NOT_EXIST = -1066;
    
    public static final String MAIL_HOST_KEY = "mail.smtp.host";
    public static final String MAIL_PORT_KEY = "mail.smtp.port";
    public static final String MAIL_AUTH_KEY = "mail.smtp.auth";
    public static final String MAIL_DEBUG_KEY = "mail.smtp.debug";
    public static final String MAIL_SSL_ENABLE_KEY = "mail.smtp.ssl.enable";
    public static final String MAIL_DEBUG = "true";
    
    public static int MAX_SIZE = -1077;
    public static int MIN_SIZE = -1078;
    
    public static int EMPTY_FILE = -1079;

    
}
