package com.example.demo2.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo2.entities.UserAccessManagement;
import com.example.demo2.entities.UserMst;
import com.example.demo2.model.dto.MailRequestDto;
import com.example.demo2.model.payload.OtpPayload;
import com.example.demo2.model.payload.UserPayload;
import com.example.demo2.model.response.CommonResoponse;
import com.example.demo2.repositories.UserRepository;
import com.example.demo2.utils.Constants;
import com.example.demo2.utils.HelperClass;
import com.example.demo2.utils.Messages;

@Service
public class UserServiceImpl<T> implements UserService {

	@Autowired
	private UserRepository userdao;

	private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public T getUsers() {
		log.info("UserServiceImpl::getUsers()=== START");
		CommonResoponse cmn = new CommonResoponse();
		try {

			log.info("UserServiceImpl::getUsers()::findAll()");
			List<UserMst> listFromDb = userdao.findAll();
			cmn.setData(listFromDb);
			cmn.setMessage(Messages.SUCCESS_MSG);
			cmn.setStatusCode(Constants.SUCCESS_CD);
			log.info("UserServiceImpl::getUsers()=== END");
			return (T) cmn;
		} catch (Exception e) {
			log.error("UserServiceImpl::getUsers():: Exception={}", e.getStackTrace());
			return (T) HelperClass.getCommonExceptionResoponse();

		}
	}

	@Override
	public T addUser(UserPayload userPayload) {

		log.info("UserServiceImpl::addUser()=== START");
		UserMst user = new UserMst();
		UserAccessManagement accessManagement = new UserAccessManagement();
		CommonResoponse cmn = new CommonResoponse();
		try {

			Optional<UserMst> userFromDb = this.userdao.fatchUser(userPayload.getMobileNumber(),
					userPayload.getEmail());
			if (userFromDb.isPresent()) {
				cmn.setMessage(Messages.ALREADY_EXIST);
				cmn.setStatusCode(Constants.USER_ALREADY_EXIST);
				return (T) cmn;
			}
			BeanUtils.copyProperties(userPayload, user);
			accessManagement.setPassword(generatePassword());
			user.setAccessManagement(accessManagement);
			log.info("UserServiceImpl::addUser()::save()");
			userdao.save(user);
			MailRequestDto mailReq = new MailRequestDto();
			mailReq.setReceiverAddress(userPayload.getEmail());
			mailReq.setSubject("Registration status");
			mailReq.setMessage("Hey, "+ userPayload.getFirstName() + " you have been registerd successfully");
			cmn.setMessage(Messages.SUCCESS_MSG);
			cmn.setStatusCode(Constants.SUCCESS_CD);
			log.info("UserServiceImpl::addUser()=== END");
			return (T) cmn;
		} catch (Exception e) {

			log.error("UserServiceImpl::addUser():: Exception={}", e.getStackTrace());
			return (T) HelperClass.getCommonExceptionResoponse();
		}

	}

	@Override
	public T updateUser(UserPayload userPayload) {

		log.info("UserServiceImpl::updateUser()=== START");
		UserMst user = new UserMst();
		CommonResoponse cmn = new CommonResoponse();
		try {
			userdao.updateName(userPayload.getId(), userPayload.getName());
			log.info("UserServiceImpl::addUser()::save()");
			userdao.save(user);
			cmn.setMessage(Messages.SUCCESS_MSG);
			cmn.setStatusCode(Constants.SUCCESS_CD);
			log.info("UserServiceImpl::addUser()=== END");
			return (T) cmn;
		} catch (Exception e) {

			log.error("UserServiceImpl::updateUser():: Exception={}", e.getStackTrace());
			return (T) HelperClass.getCommonExceptionResoponse();
		}
	}

	@Override
	public T searchUser(String search) {

		log.info("UserServiceImpl::searchUser()=== START");
		CommonResoponse cmn = new CommonResoponse();
		List<UserMst> listFromDb;
		try {

			if (search == null || search.equalsIgnoreCase("")) {
				log.info("UserServiceImpl::searchUser()::findAll()");
				listFromDb = userdao.findAll();
			} else {
				log.info("UserServiceImpl::searchUser()::searchUser()");
				listFromDb = userdao.searchUser(search.toUpperCase());

			}

			cmn.setData(listFromDb);
			cmn.setMessage(Messages.SUCCESS_MSG);
			cmn.setStatusCode(Constants.SUCCESS_CD);

			log.info("UserServiceImpl::searchUser()=== END");
			return (T) cmn;
		} catch (Exception e) {
			log.error("UserServiceImpl::searchUser():: Exception={}", e.getStackTrace());
			return (T) HelperClass.getCommonExceptionResoponse();

		}

	}

	private String generatePassword() {
		
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder();
		for(int i =1; i<=7; i++) {
			int j = rnd.nextInt(Constants.PASSWORD_HELPER.length());
			sb.append(Constants.PASSWORD_HELPER.charAt(j));
			
		}
		return sb.toString();
		
	}
	

}
