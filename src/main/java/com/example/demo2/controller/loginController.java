package com.example.demo2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo2.model.payload.LoginPayload;
import com.example.demo2.model.response.CommonResoponse;
import com.example.demo2.service.LoginService;
import com.example.demo2.utils.HelperClass;
import com.example.demo2.utils.MailService;


@RestController
public class loginController<T> {

	
	@Autowired
	private LoginService<T> loginService;
	

	
	private final static Logger log = LoggerFactory.getLogger(loginController.class);
	
	@PostMapping("/login")
	public ResponseEntity<T> login(@RequestBody LoginPayload loginPayload){
		log.info("loginController::login===START ");
		CommonResoponse cmn = new CommonResoponse();
		try {

			
			if(loginPayload== null || loginPayload.getCaptchaCode()== null || loginPayload.getCaptchaId()== null ||
					loginPayload.getUsername()== null || loginPayload.getPassword() == null) {
				log.error("loginController::login::ERROR= Invalid request");
				cmn = HelperClass.getNullRequestResoponse();
				log.error("loginController::login::ERROR= {}", cmn);
				return (ResponseEntity<T>) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cmn);
			}
			log.info("loginController::login::login() ");
			cmn = (CommonResoponse) loginService.login(loginPayload);

			log.info("loginController::login===END ");
			return ResponseEntity.status(HttpStatus.OK).body((T) cmn);
		} catch (Exception e) {

			log.error("loginController::login===EXCEPTION= {} ", e.getStackTrace());
			cmn = HelperClass.getCommonExceptionResoponse();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((T) cmn);
		}
		
	}
}
