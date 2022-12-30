package com.example.demo2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo2.model.response.CommonResoponse;
import com.example.demo2.service.CaptchaService;
import com.example.demo2.utils.HelperClass;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {

	
	@SuppressWarnings("rawtypes")
	@Autowired
	private CaptchaService captchaService;
	
	
	
	private final static Logger log = LoggerFactory.getLogger(CaptchaController.class);

	
	@SuppressWarnings("unchecked")
	@GetMapping("/generate")
	public <T> ResponseEntity<T> generateCaptcha() {
		log.info("CaptchaController::generateCaptcha===START ");
		CommonResoponse cmn = new CommonResoponse();
		try {

			log.info("CaptchaController::generateCaptcha::generateCaptcha() ");
			cmn = (CommonResoponse) captchaService.generateCaptcha();
			
			log.info("CaptchaController::generateCaptcha===END ");
			return (ResponseEntity<T>) ResponseEntity.status(HttpStatus.OK).body((T) cmn);
			
		} catch (Exception e) {

			log.error("CaptchaController::generateCaptcha===EXCEPTION= {} ", e.getStackTrace());
			cmn = HelperClass.getCommonExceptionResoponse();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((T) cmn);
		}
		
}

}