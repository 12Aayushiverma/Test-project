package com.example.demo2.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo2.config.JwtUserDetailsService;
import com.example.demo2.config.JwtUtil;
import com.example.demo2.config.JwtUtil;
import com.example.demo2.entities.UserMst;
import com.example.demo2.model.payload.LoginPayload;
import com.example.demo2.model.response.CommonResoponse;
import com.example.demo2.repositories.CaptchaRepository;
import com.example.demo2.repositories.UserRepository;
import com.example.demo2.utils.Constants;
import com.example.demo2.utils.HelperClass;
import com.example.demo2.utils.Messages;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class LoginServiceImpl<T> implements LoginService<T> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CaptchaService captchaService;

	@Autowired
	private JwtUserDetailsService detailsService;

	@Autowired
	private JwtUtil jwtutils;

	private final static Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public T login(LoginPayload loginPayload) {
		log.info("LoginServiceImpl::loginUser()=== START");
		CommonResoponse cmn = new CommonResoponse();
		try {

			log.info("LoginServiceImpl::loginUser()::validateCaptcha");
			cmn = (CommonResoponse) captchaService.validateCaptcha(loginPayload.getCaptchaId(),
					loginPayload.getCaptchaCode());
			if (cmn.getStatusCode() != Constants.CAPTCHA_VALIDATED_SUCCESS_CD) {
				log.error("LoginServiceImpl::loginUser()::validateCaptcha::INVALID_CAPTCHA");
				return (T) cmn;
			}
			log.info("LoginServiceImpl::loginUser()::findByEmailOrMobileNumber()");
			Optional<UserMst> userFromDb = this.userRepository.findByEmailOrMobileNumber(loginPayload.getUsername(),
					loginPayload.getUsername());
			if (userFromDb.isPresent()) {

				if (loginPayload.getPassword().equals(userFromDb.get().getPassword())) {

					log.info("LoginServiceImpl::loginUser()::loadUserByUsername()");
					UserDetails userDetails = this.detailsService.loadUserByUsername(loginPayload.getUsername());
					log.info("LoginServiceImpl::loginUser()::generateToken()");
					Map<String, Object> claims = new HashMap<String, Object>();
					claims.put(Constants.MOBILE_NO_KEY, userFromDb.get().getMobileNumber());
					String token = jwtutils.generateToken(userDetails, claims);
					HashMap<String , String > res = new HashMap<>();
					res.put(Constants.JWT_KEY, token);
					cmn.setData(res);
					cmn.setMessage(Messages.SUCCESS_MSG);
					cmn.setStatusCode(Constants.SUCCESS_CD);
					log.info("LoginServiceImpl::loginUser()=== END");
					return (T) cmn;

				} else {
					log.error("LoginServiceImpl::loginUser::ERROR= {}", Messages.INVALID_PASSWORD);
					cmn.setMessage(Messages.INVALID_PASSWORD);
					cmn.setStatusCode(Constants.INVALID_PASSWORD);
					return (T) cmn;

				}

			} else {
				log.error("LoginServiceImpl::loginUser::ERROR= {}", Messages.USER_NOT_EXIST);
				cmn.setMessage(Messages.USER_NOT_EXIST);
				cmn.setStatusCode(Constants.USER_NOT_EXIST);
				return (T) cmn;

			}
		} catch (Exception e) {
			log.error("LoginServiceImpl::loginUser():: Exception={}", e.getStackTrace());
			return (T) HelperClass.getCommonExceptionResoponse();
		}

	}

}
