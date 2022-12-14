package com.example.demo2.service;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo2.dao.OtpDao;
import com.example.demo2.dao.UserDao;
import com.example.demo2.entities.OtpDtls;
import com.example.demo2.model.dto.SendSmsDto;
import com.example.demo2.model.payload.OtpPayload;
import com.example.demo2.model.response.CommonResoponse;
import com.example.demo2.utils.Constants;
import com.example.demo2.utils.HelperClass;
import com.example.demo2.utils.TwilioService;

@Service
public class UtilityServiceImpl<T> implements UtilityService {

	@Autowired
	private OtpDao otpDao;

	private final static Logger log = LoggerFactory.getLogger(UtilityServiceImpl.class);

	@Override
	public T sendOtp(OtpPayload otpPayload) {
		log.info("UtilityServiceImpl::sendOtp()=== START");
		CommonResoponse cmn = new CommonResoponse();
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();

			OtpDtls otpDtls = new OtpDtls();
			otpDtls.setOtp(getOtp());
			otpDtls.setOtpTxnId(getOtpTxnId());
			otpDtls.setMobileNo(otpPayload.getMobileNo());
			otpDtls.setType(otpPayload.getType());
			otpDao.save(otpDtls);
			
			TwilioService twilioService = new TwilioService();
			SendSmsDto sendSmsDto = new SendSmsDto(otpPayload.getMobileNo(), Integer.parseInt(otpDtls.getOtp()));
			twilioService.otpSendToMobile(sendSmsDto);
			map.put(Constants.OTP_TXN_ID_KEY, otpDtls.getOtpTxnId());
			cmn.setMessage("OTP has been sent on your mobile number " + "XX" + otpPayload.getMobileNo().substring(2, 8)
					+ "XX");
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
		return null;
	}

	private static String getOtp() {
		SecureRandom secureRdm = new SecureRandom();
		int number = secureRdm.nextInt(9999);
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

	public static void main(String[] args) {

		String mobileNo = "9179176749";
		System.out.println("XX" + mobileNo.substring(2, 8) + "XX");

	}

}
