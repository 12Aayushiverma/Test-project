package com.example.demo2.service;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo2.config.JwtUtil;
import com.example.demo2.entities.OtpDtls;
import com.example.demo2.entities.UserMst;
import com.example.demo2.model.dto.SendSmsDto;
import com.example.demo2.model.payload.OtpPayload;
import com.example.demo2.model.response.CommonResoponse;
import com.example.demo2.repositories.OtpRepository;
import com.example.demo2.repositories.UserRepository;
import com.example.demo2.utils.Constants;
import com.example.demo2.utils.HelperClass;
import com.example.demo2.utils.Messages;
import com.example.demo2.utils.TwilioService;

import ch.qos.logback.classic.pattern.Util;

@Service
public class UtilityServiceImpl<T> implements UtilityService<T> {

	@Autowired
	private OtpRepository otpDao;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	JwtUtil jwtUtil;

	private final static Logger log = LoggerFactory.getLogger(UtilityServiceImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public T sendOtp(OtpPayload otpPayload) {
		log.info("UtilityServiceImpl::sendOtp()=== START");
		CommonResoponse cmn = new CommonResoponse();
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			String generatedOtp = null;
			String otpTxnId = null;
			OtpDtls otpDtls = new OtpDtls();
			Optional<OtpDtls> otpOptional = null;
			String mobileNo = null;
			String email = null;
			if (otpPayload.getType().equalsIgnoreCase("forgetPassword")) {
				Optional<UserMst> userFromDb = this.userRepository.findByEmailOrMobileNumber(otpPayload.getId(),
						otpPayload.getId());
				mobileNo = userFromDb.get().getMobileNumber();
				email = userFromDb.get().getEmail();
				otpOptional = this.otpDao.check(userFromDb.get().getMobileNumber(), otpPayload.getType());
			} else if (otpPayload.getType().equalsIgnoreCase("mobileAuth")) {
				mobileNo = otpPayload.getMobileNo();
				otpOptional = this.otpDao.check(otpPayload.getMobileNo(), otpPayload.getType());
			}

			if (otpOptional.isPresent()) {

				generatedOtp = getOtp();

				otpTxnId = getOtpTxnId();

				this.otpDao.updateOtpDtls(generatedOtp, getOtpTxnId(), otpOptional.get().getId());
				log.info("UtilityServiceImpl::sendOtp()::update()");

			} else {
				generatedOtp = getOtp();
				otpTxnId = getOtpTxnId();
				otpDtls.setOtp(generatedOtp);
				otpDtls.setOtpTxnId(otpTxnId);
				otpDtls.setMobileNo(mobileNo);
				otpDtls.setEmail(email);
				otpDtls.setType(otpPayload.getType());
				log.info("UtilityServiceImpl::sendOtp()::save()");
				otpDao.save(otpDtls);
			}

			TwilioService twilioService = new TwilioService();
			SendSmsDto sendSmsDto = new SendSmsDto(mobileNo, Integer.parseInt(generatedOtp));
			log.info("UtilityServiceImpl::sendOtp()::otpSendToMobile()");
			twilioService.otpSendToMobile(sendSmsDto);
			map.put(Constants.OTP_TXN_ID_KEY, otpTxnId);
			cmn.setMessage(Messages.OTP_SUCCESS_MSG_TEMPLATE.replace("{0}", "XX" + mobileNo.substring(2, 8) + "XX"));
			cmn.setStatusCode(Constants.SUCCESS_CD);
			cmn.setData(map);

			log.info("UtilityServiceImpl::sendOtp()=== END");
			return (T) cmn;

		} catch (Exception e) {
			log.error("UtilityServiceImpl::sendOtp():: Exception={}", e.getStackTrace());
			return (T) HelperClass.getCommonExceptionResoponse();

		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public T validateOtp(OtpPayload otpPayload) {
		log.info("UtilityServiceImpl::validateOtp()=== START");
		CommonResoponse cmn = new CommonResoponse();
		Optional<OtpDtls> otpOptional = null;
		String mobileNo = null;
		String email = null;
		try {
      
			log.info("UtilityServiceImpl::validateOtp()::getOtpDtls()");
			if(otpPayload.getType().equalsIgnoreCase("forgetPassword")) {
			Optional<UserMst>	userFromDb =userRepository.findByEmailOrMobileNumber(otpPayload.getId(), otpPayload.getId());
			mobileNo = userFromDb.get().getMobileNumber();
			email = userFromDb.get().getEmail();
			}
			else if(otpPayload.getType().equalsIgnoreCase("mobileAuth")) {
				mobileNo = otpPayload.getMobileNo();
				
			}
			otpOptional= this.otpDao.getOtpDtls(mobileNo, otpPayload.getType());
			if (otpOptional.isPresent()) {
				if (otpOptional.get().getOtp().equalsIgnoreCase(otpPayload.getOtp())
						&& otpOptional.get().getOtpTxnId().equalsIgnoreCase(otpPayload.getOtpTxnId())) {
					// log.info("UtilityServiceImpl::validateOtp()::deleteOtpDtls()");
					// this.otpDao.deleteOtpDtls(otpOptional.get().getId());
					cmn.setMessage(Messages.OTP_SUCCESS_MSG);
					cmn.setStatusCode(Constants.OTP_SUCCESS_CD);
					log.info("UtilityServiceImpl::validateOtp()=== END");
					return (T) cmn;
				} else {
					cmn.setMessage(Messages.INVALID_OTP);
					cmn.setStatusCode(Constants.INVALID_OTP);
					log.error("UtilityServiceImpl::validateOtp()::ERROR=INVALID_OTP");
					return (T) cmn;
				}
			} else {
				cmn.setMessage(Messages.OTP_REQUEST);
				cmn.setStatusCode(Constants.REQUEST_FOR_OTP_CD);
				log.error("UtilityServiceImpl::validateOtp()::ERROR= ()", Messages.OTP_REQUEST);
				return (T) cmn;
			}

		} catch (Exception e) {
			log.error("UtilityServiceImpl::validateOtp():: Exception={}", e.getStackTrace());
			return (T) HelperClass.getCommonExceptionResoponse();
		}

	}

	private static String getOtp() {
		SecureRandom secureRdm = new SecureRandom();
		int number = secureRdm.nextInt(999999);
		return String.format("%06d", number);

	}

	private static String getOtpTxnId() {

		log.info("UtilityServiceImpl::getOtpTxnId()=== START");

		try {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("ddhhmmss");
			return sdf.format(date);

		} catch (Exception e) {

			log.error("UtilityServiceImpl::getOtpTxnId()");
			return null;
		}

	}
	
	@Override
	public String getMobileNoFromToken() {
		String token = request.getHeader("Authorization").substring(7);
		 return  jwtUtil.getMobileNoFromToken(token);
	}

}
