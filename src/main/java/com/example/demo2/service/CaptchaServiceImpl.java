package com.example.demo2.service;

import java.util.HashMap;
import java.util.Map;
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

@Service
public class CaptchaServiceImpl<T> implements CaptchaService<T> {

	@Autowired
	private CaptchaRepository captchaRepository;

	private final static Logger log = LoggerFactory.getLogger(CaptchaServiceImpl.class);

	@Override
	public T generateCaptcha() {
		log.info("CaptchaServiceImpl::generateCaptcha=== START");
		CommonResoponse cmn = new CommonResoponse();

		try {

			String captchaCode = doGenerateCaptcha();
			log.info("CaptchaServiceImpl::generateCaptcha::save()");
			CaptchaDtls generatedCaptchaDtls = this.captchaRepository.save(new CaptchaDtls(captchaCode));
			Map<String, Object> captchaDtls = new HashMap<String, Object>();
			captchaDtls.put("captchaCode", generatedCaptchaDtls.getCaptchaCode());
			captchaDtls.put("captchaId", generatedCaptchaDtls.getCaptchaId());
			cmn.setData(captchaDtls);
			cmn.setMessage("Captcha code has been generated successfully!");
			cmn.setStatusCode(1080);
			log.info("CaptchaServiceImpl::generateCaptcha=== END");

			return (T) cmn;
		} catch (Exception e) {
			log.error("CaptchaServiceImpl::generateCaptcha:: Exception={}", e.getStackTrace());
			return (T) HelperClass.getCommonExceptionResoponse();
		}

	}

	@Override
	public T validateCaptcha(int captchaId, String captchaCode) {

		return null;
	}

	private String doGenerateCaptcha() {
		log.info("CaptchaServiceImpl::doGenerateCaptcha===START");
   try {
	

		Random rnd = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 4; i++) {
			int j = rnd.nextInt(Constants.CAPTCHA_HELPER.length());
			sb.append(Constants.CAPTCHA_HELPER.charAt(j));

		}
		log.info("CaptchaServiceImpl::doGenerateCaptcha===END");

		return sb.toString();

	}catch (Exception e) {
		log.error("CaptchaServiceImpl::doGenerateCaptcha:: Exception={}", e.getStackTrace());
		return null;
	}
   }
}
