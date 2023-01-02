package com.example.demo2.controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo2.model.payload.UserPayload;
import com.example.demo2.model.response.CommonResoponse;
import com.example.demo2.service.UserService;
import com.example.demo2.utils.Constants;
import com.example.demo2.utils.HelperClass;
import com.example.demo2.utils.Messages;

@RestController
@RequestMapping("/user")
public class UserController<T> {

	@Autowired
	private UserService userService;
	


	private final static Logger log = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/users")
	public ResponseEntity<T> getUsers(@RequestParam("pageNo") int pageNo) {
		log.info("MyController::getUsers===START ");
		CommonResoponse cmn = new CommonResoponse();
		try {

			log.info("MyController::getUsers::getUsers() ");
			cmn = (CommonResoponse) userService.getUsers(pageNo);

			log.info("MyController::getUser===END ");
			return ResponseEntity.status(HttpStatus.OK).body((T) cmn);
		} catch (Exception e) {

			log.error("MyController::getUser===EXCEPTION= {} ", e.getStackTrace());
			cmn = HelperClass.getCommonExceptionResoponse();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body((T) cmn);
		}
	}

	@PostMapping("/register")
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

	@PutMapping("/update/user")
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

	@GetMapping("/find")
	public ResponseEntity<T> searchUsers(@RequestParam(value = "search", required = false) String search) {

		log.info("MyController::searchUsers===START ");
		CommonResoponse cmn = new CommonResoponse();
		try {

			log.info("MyController::searchUser()");
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

	@PostMapping("/uploadProfile")
	public T profileUpload(@RequestParam(value = "profileImg") MultipartFile file,
			@RequestParam(value = "userId") String userId) {
		log.info("MyController::profileUpload===START ");
		CommonResoponse cmn = new CommonResoponse();
		try {
			if (file == null) {
				log.error("MyController::profileUpload::ERROR= Invalid request");
				cmn = HelperClass.getNullRequestResoponse();
				return (T) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cmn);

			} else if (file.getSize() < 1024) {
				log.error("MyController::profileUpload::ERROR= {}", cmn);
				cmn.setMessage(Messages.MIN_SIZE_ALLOWED);
				cmn.setStatusCode(Constants.MAX_SIZE);
				return (T) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cmn);
			} else if (file.getSize() > 1048576) {
				log.error("MyController::profileUpload::ERROR= {}", cmn);
				cmn.setMessage(Messages.MAX_SIZE_ALLOWED);
				cmn.setStatusCode(Constants.MIN_SIZE);
				return (T) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cmn);
			}
			log.info("MyController::profileUpload::uploadProfile():: request= {}");
			cmn = (CommonResoponse) userService.uploadProfile(file, userId);

			log.info("MyController::profileUpload===END ");
			return (T) ResponseEntity.status(HttpStatus.OK).body(cmn);

		} catch (Exception e) {

			log.error("MyController::profileUpload===EXCEPTION= {} ", e.getStackTrace());
			cmn.setMessage(Messages.SOMETHING_WENT_WRONG);
			cmn.setStatusCode(Constants.ERROR_CD);
			return (T) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cmn);
		}

		

	}

}
