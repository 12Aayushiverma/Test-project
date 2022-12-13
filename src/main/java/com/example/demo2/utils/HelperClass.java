package com.example.demo2.utils;

import com.example.demo2.model.response.CommonResoponse;

public class HelperClass {

	   public static CommonResoponse getNullRequestResoponse() {
		   CommonResoponse  commonResoponse = new  CommonResoponse();
		   commonResoponse.setMessage(Messages.NULL_REQUEST);
		   commonResoponse.setStatusCode(Constants.NULL_REQUEST);
		   
		   return  commonResoponse;
		   
	   }
	   
	   public static CommonResoponse getCommonExceptionResoponse() {
		   CommonResoponse  commonResoponse = new  CommonResoponse();
		   commonResoponse.setMessage(Messages.SOMETHING_WENT_WRONG);
		   commonResoponse.setStatusCode(Constants.ERROR_CD);
		   
		   return  commonResoponse;
		   
	   }
}
