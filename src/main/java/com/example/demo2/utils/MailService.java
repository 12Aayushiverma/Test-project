package com.example.demo2.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo2.controller.loginController;
import com.example.demo2.model.dto.MailRequestDto;

import java.util.Properties;

import javax.mail.*;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.*;

@Service
public class MailService {

	private static String username = "testportal073@mail.com";

//	@Value("${spring.eMailSenderCrediationals.password}")
	private static String password = "gzhexshvsmabvvqi";

	private final static Logger log = LoggerFactory.getLogger(loginController.class);

	public static Session connectionToSmtp() {

		Properties prop = new Properties();
		prop.put(Constants.MAIL_HOST_KEY, "smtp.gmail.com");
		prop.put(Constants.MAIL_PORT_KEY, "587");
		prop.put(Constants.MAIL_AUTH_KEY, Constants.MAIL_DEBUG);
		prop.put("mail.smtp.starttls.enable", Constants.MAIL_DEBUG);

		log.info("username: " + username + " password: " + password);

		return Session.getInstance(prop, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}

	public void sendMail(MailRequestDto mailRequestDto, String username , String password) {
		try {

			Message message = new MimeMessage(connectionToSmtp());
			message.setFrom(new InternetAddress(mailRequestDto.getReceiverAddress()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailRequestDto.getReceiverAddress()));

			message.setSubject("Credentials");
			message.setText("your username = "+username + "and password = "+password);
			Transport.send(message);

		} catch (Exception e) {
			
		}

	}

}
