package com.example.demo2.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo2.entities.CaptchaDtls;
import com.example.demo2.model.response.CommonResoponse;
import com.example.demo2.repositories.CaptchaRepository;
import com.example.demo2.utils.Constants;
import com.example.demo2.utils.HelperClass;
import com.example.demo2.utils.Messages;

/**
 * @author Aayushi Verma
 * @version 1.0
 * @apiNote It is a captcha code module for generate and validate captcha code
 */
@Service
public class CaptchaServiceImpl<T> implements CaptchaService<T> {

	@Autowired
	private CaptchaRepository captchaRepository;

	private final static Logger log = LoggerFactory.getLogger(CaptchaServiceImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public T generateCaptcha() {
		log.info("CaptchaServiceImpl::generateCaptcha=== START");
		CommonResoponse cmn = new CommonResoponse();

		try {

			String captchaCode = (String) doGenerateCaptcha();
			log.info("CaptchaServiceImpl::generateCaptcha::save()");
			CaptchaDtls generatedCaptchaDtls = this.captchaRepository.save(new CaptchaDtls(captchaCode));
			Map<String, Object> captchaDtls = new HashMap<String, Object>();
			captchaDtls.put("captchaCode", generatedCaptchaDtls.getCaptchaCode());
			captchaDtls.put("captchaId", generatedCaptchaDtls.getCaptchaId());
			cmn.setData(captchaDtls);
			cmn.setMessage(Messages.CAPTCHA_CODE_GENERATE);
			cmn.setStatusCode(Constants.CAPTCHA_CODE_GENERATE);
			log.info("CaptchaServiceImpl::generateCaptcha=== END");

			return (T) cmn;
		} catch (Exception e) {
			log.error("CaptchaServiceImpl::generateCaptcha:: Exception={}", e.getStackTrace());
			return (T) HelperClass.getCommonExceptionResoponse();
		}

	}

	@Override
	public T validateCaptcha(Integer captchaId, String captchaCode) {

		log.info("CaptchaServiceImpl::validateCaptcha=== START");
		CommonResoponse cmn = new CommonResoponse();

		try {
			Optional<CaptchaDtls> validatedCaptchaResult = this.captchaRepository
					.findByCaptchaCodeAndCaptchaId(captchaCode, captchaId);
			if (validatedCaptchaResult.isPresent()) {
				cmn.setStatusCode(Constants.CAPTCHA_VALIDATED_SUCCESS_CD);
				// TODO Delete code need to be add
				log.info("CaptchaServiceImpl::validateCaptcha=== END");

			} else {
				cmn.setMessage("Invalid captcha code!");
				cmn.setStatusCode(Constants.INVALID_CAPTCHA_CD);
				log.error("CaptchaServiceImpl::validateCaptcha()::ERROR=INVALID_CAPTCHA_CODE ");
			}
			return (T) cmn;

		} catch (Exception e) {

			log.error("CaptchaServiceImpl::generateCaptcha:: Exception={}", e.getStackTrace());
			return (T) HelperClass.getCommonExceptionResoponse();

		}

	}

	private T doGenerateCaptcha() {
		log.info("CaptchaServiceImpl::doGenerateCaptcha===START");
		try {

			Random rnd = new Random();
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i <= 4; i++) {
				int j = rnd.nextInt(Constants.CAPTCHA_HELPER.length());
				sb.append(Constants.CAPTCHA_HELPER.charAt(j));

			}
			log.info("CaptchaServiceImpl::doGenerateCaptcha===END");

			return (T) sb.toString();

		} catch (Exception e) {
			log.error("CaptchaServiceImpl::doGenerateCaptcha:: Exception={}", e.getStackTrace());
			return (T) HelperClass.getCommonExceptionResoponse();
		}
	}
}
