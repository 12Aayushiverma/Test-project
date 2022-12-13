package com.example.demo2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo2.model.payload.UserPayload;
import com.example.demo2.model.response.CommonResoponse;
import com.example.demo2.service.UserService;
import com.example.demo2.utils.Constants;
import com.example.demo2.utils.HelperClass;
import com.example.demo2.utils.Messages;

@RestController
public class MyController<T> {

	@Autowired
	private UserService userService;

	private final static Logger log = LoggerFactory.getLogger(MyController.class);

	@GetMapping("/Users")
	public ResponseEntity<T> getUsers() {
		log.info("MyController::getUsers===START ");
		CommonResoponse cmn = new CommonResoponse();
		try {

			log.info("MyController::getUsers()::UserId = () ");
			cmn = (CommonResoponse) userService.getUsers();

			log.info("MyController::getUser===END ");
			return ResponseEntity.status(HttpStatus.OK).body((T) cmn);
		} catch (Exception e) {

			log.error("MyController::getUser===EXCEPTION= {} ", e.getStackTrace());
			cmn = HelperClass.getCommonExceptionResoponse();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((T) cmn);
		}
	}

	@PostMapping("/user")
	public ResponseEntity<CommonResoponse> addUser(@RequestBody UserPayload user) {

		log.info("MyController::addUser===START ");
		CommonResoponse cmn = new CommonResoponse();
		try {

			if (user == null) {

				log.error("MyController::addUser::ERROR= Invalid request");
				cmn = HelperClass.getNullRequestResoponse();
				log.error("MyController::addUser::ERROR= {}", cmn);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cmn);
			} else if (user.getMobileNumber() == null || !user.getMobileNumber().matches(Constants.MOBILE_NO_REGEX)) {
				log.error("MyController::addUser::ERROR= {}", Messages.INVALID_MOBILE_NUMBER);
				cmn.setMessage(Messages.INVALID_MOBILE_NUMBER);
				cmn.setStatusCode(Constants.INVALID_MOBILE_NO_CD);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cmn);

			} else if (user.getEmail() == null || !user.getEmail().matches(Constants.EMAIL_REGEX)) {
				log.error("MyController::addUser::ERROR= {}", Messages.INVALID_EMAIL);

				cmn.setMessage(Messages.INVALID_EMAIL);
				cmn.setStatusCode(Constants.INVALID_EMAIL_CD);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cmn);

			}
			log.info("MyController::addUser::addUser():: request= {}", user);
			cmn = (CommonResoponse) userService.addUser(user);

			log.info("MyController::addUser===END ");
			return ResponseEntity.status(HttpStatus.OK).body(cmn);
		} catch (Exception e) {

			log.error("MyController::AddUser===EXCEPTION= {} ", e.getStackTrace());
			cmn.setMessage(Messages.SOMETHING_WENT_WRONG);
			cmn.setStatusCode(Constants.ERROR_CD);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmn);
		}

	}

	@PutMapping("/user")
	public ResponseEntity<CommonResoponse> updateUser(@RequestBody UserPayload user) {

		log.info("MyController::updateUser===START ");
		CommonResoponse cmn = new CommonResoponse();
		try {

			log.info("MyController::updateUser()::UserId = () ");
			Object userFromDB = userService.updateUser(user);

			cmn.setData(userFromDB);
			cmn.setStatusCode(Constants.SUCCESS_CD);
			cmn.setMessage(Messages.SUCCESS_MSG);

			log.info("MyController::getUser===END ");
			return ResponseEntity.status(HttpStatus.OK).body(cmn);
		} catch (Exception e) {

			log.error("MyController::getUser===EXCEPTION= {} ", e.getStackTrace());
			cmn.setMessage(Messages.SOMETHING_WENT_WRONG);
			cmn.setStatusCode(Constants.ERROR_CD);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmn);
		}

	}

	@GetMapping("/user")
	public ResponseEntity<T> searchUsers(@RequestParam(value = "search", required = false) String search) {

		log.info("MyController::searchUsers===START ");
		CommonResoponse cmn = new CommonResoponse();
		try {

			log.info("MyController::searchUsers()");
			cmn = (CommonResoponse) userService.searchUser(search);

			log.info("MyController::searchUsers===END ");
			return ResponseEntity.status(HttpStatus.OK).body((T) cmn);

		}

		catch (Exception e) {
			log.error("MyController::searchUsers===EXCEPTION= {} ", e.getStackTrace());
			cmn = HelperClass.getCommonExceptionResoponse();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((T) cmn);
		}

	}

	public static void main(String[] args) {

		System.out.println("Hello world");

		String mobileNo = "919179176749";

		String email = "av902800@gmail.in";

		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		if (email.matches(emailRegex)) {

			System.out.println("valid");
		} else {
			System.out.println("Invalid");
		}

	}

}
