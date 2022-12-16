package com.example.demo2.service;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo2.entities.OtpDtls;
import com.example.demo2.model.dto.SendSmsDto;
import com.example.demo2.model.payload.OtpPayload;
import com.example.demo2.model.response.CommonResoponse;
import com.example.demo2.repositories.OtpRepository;
import com.example.demo2.utils.Constants;
import com.example.demo2.utils.HelperClass;
import com.example.demo2.utils.Messages;
import com.example.demo2.utils.TwilioService;

@Service
public class UtilityServiceImpl<T> implements UtilityService<T> {

	@Autowired
	private OtpRepository otpDao;

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
			Optional<OtpDtls> otpOptional = this.otpDao.check(otpPayload.getMobileNo(), otpPayload.getType());
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
				otpDtls.setMobileNo(otpPayload.getMobileNo());
				otpDtls.setType(otpPayload.getType());
				log.info("UtilityServiceImpl::sendOtp()::save()");
				otpDao.save(otpDtls);
			}

			TwilioService twilioService = new TwilioService();
			SendSmsDto sendSmsDto = new SendSmsDto(otpPayload.getMobileNo(), Integer.parseInt(generatedOtp));
			log.info("UtilityServiceImpl::sendOtp()::otpSendToMobile()");
			twilioService.otpSendToMobile(sendSmsDto);
			map.put(Constants.OTP_TXN_ID_KEY, otpTxnId);
			cmn.setMessage(Messages.OTP_SUCCESS_MSG_TEMPLATE.replace("{0}",
					"XX" + otpPayload.getMobileNo().substring(2, 8) + "XX"));
			cmn.setStatusCode(Constants.SUCCESS_CD);
			cmn.setData(map);

			log.info("UtilityServiceImpl::sendOtp()=== END");
			return (T) cmn;

		} catch (Exception e) {
			log.error("UtilityServiceImpl::sendOtp():: Exception={}", e.getStackTrace());
			return (T) HelperClass.getCommonExceptionResoponse();

		}

	}

	@Override
	public T validateOtp(OtpPayload otpPayload) {
		log.info("UtilityServiceImpl::validateOtp()=== START");
		CommonResoponse cmn = new CommonResoponse();
		try {

			log.info("UtilityServiceImpl::validateOtp()::getOtpDtls()");
			Optional<OtpDtls> otpOptional = this.otpDao.getOtpDtls(otpPayload.getMobileNo(), otpPayload.getType());
			if (otpOptional.isPresent()) {
				if (otpOptional.get().getOtp().equalsIgnoreCase(otpPayload.getOtp())
						&& otpOptional.get().getOtpTxnId().equalsIgnoreCase(otpPayload.getOtpTxnId())) {
					// log.info("UtilityServiceImpl::validateOtp()::deleteOtpDtls()");
//                    this.otpDao.deleteOtpDtls(otpOptional.get().getId());
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

}
