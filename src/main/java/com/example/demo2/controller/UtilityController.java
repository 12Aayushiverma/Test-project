package com.example.demo2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo2.model.payload.OtpPayload;
import com.example.demo2.model.response.CommonResoponse;
import com.example.demo2.service.UtilityService;
import com.example.demo2.utils.HelperClass;

@RestController
@RequestMapping("/utility")
public class UtilityController<T> {

	private final static Logger log = LoggerFactory.getLogger(UtilityController.class);

	@Autowired
	private UtilityService<T> utilityService;

	@SuppressWarnings("unchecked")
	@PostMapping("/sendotp")
	public ResponseEntity<T> sendOtp(@RequestBody OtpPayload otpPayload) {

		log.info("UtilityController::sendOtp===START ");
		CommonResoponse cmn = new CommonResoponse();
		try {

			if (otpPayload == null || otpPayload.getType() == null || otpPayload.getType().isEmpty()) {
				log.error("UtilityController::sendOtp::ERROR= Invalid request");
				cmn = HelperClass.getNullRequestResoponse();
				log.error("UtilityController::sendOtp::ERROR= {}", cmn);
				return (ResponseEntity<T>) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cmn);

			}
			log.info("UtilityController::sendOtp() ");
			cmn = (CommonResoponse) this.utilityService.sendOtp(otpPayload);

			log.info("UtilityController::sendOtp===END ");
			return ResponseEntity.status(HttpStatus.OK).body((T) cmn);

		} catch (Exception e) {

			log.error("UtilityController::sendOtp===EXCEPTION= {} ", e.getStackTrace());
			cmn = HelperClass.getCommonExceptionResoponse();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((T) cmn);

		}

	}

	@SuppressWarnings("unchecked")
	@PostMapping("/validateotp")
	public ResponseEntity<T> validateOtp(@RequestBody OtpPayload otpPayload) {

		log.info("UtilityController::validateOtp===START ");
		CommonResoponse cmn = new CommonResoponse();
		try {
			if (otpPayload == null || otpPayload.getType() == null || otpPayload.getType().isEmpty()
					|| otpPayload.getOtpTxnId() == null || otpPayload.getOtpTxnId().isEmpty()
					|| otpPayload.getOtp() == null || otpPayload.getOtp().isEmpty()) {
				log.error("UtilityController::validateOtp::ERROR= Invalid request");
				cmn = HelperClass.getNullRequestResoponse();
				log.error("UtilityController::validateOtp::ERROR= {}", cmn);
				return (ResponseEntity<T>) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cmn);

			}
			log.info("UtilityController::validateOtp() ");
			cmn = (CommonResoponse) this.utilityService.validateOtp(otpPayload);

			log.info("UtilityController::validateOtp===END ");
			return ResponseEntity.status(HttpStatus.OK).body((T) cmn);

		} catch (Exception e) {

			log.error("UtilityController::validateOtp===EXCEPTION= {} ", e.getStackTrace());
			cmn = HelperClass.getCommonExceptionResoponse();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((T) cmn);

		}

	}

}
