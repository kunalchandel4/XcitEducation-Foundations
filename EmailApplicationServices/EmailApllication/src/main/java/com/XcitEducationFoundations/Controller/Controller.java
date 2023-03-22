package com.XcitEducationFoundations.Controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.security.auth.login.LoginException;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.XcitEducationFoundations.Entity.CurrentUserSession;
import com.XcitEducationFoundations.Entity.EmailBody;
import com.XcitEducationFoundations.Exception.UserException;
import com.XcitEducationFoundations.Service.EmailService;
import com.XcitEducationFoundations.Service.LogInService;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin("*")
@RestController
public class Controller {

	@Autowired
	private EmailService emailService;

	@Autowired
	private LogInService logService;

	@Autowired
	private ObjectMapper mapper;

	private org.slf4j.Logger logger = LoggerFactory.getLogger(Controller.class);

	private CurrentUserSession check;

	@PostMapping("/sendEmail")
	public ResponseEntity<?> SendEmailHandler(@Valid @RequestBody EmailBody body, @RequestParam String uuid)
			throws UserException, MessagingException, LoginException {

		this.setCheck(logService.getSessionByUuid(uuid));
		String result = emailService.sendTextToEmail(body);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}


	@PostMapping(value = "/sendEmailWithAttachment")
	public ResponseEntity<?> SendEmailWithAttachmentHandler(@RequestParam("uuid") String uuid,
			@RequestParam("body") String body, @RequestParam("file") MultipartFile file)
			throws UserException, MessagingException, LoginException, IOException {

		this.setCheck(logService.getSessionByUuid(uuid));

		
//		logger.info(" Body {} ", body);

//		converting STring into Json

		EmailBody mainBody = mapper.readValue(body, EmailBody.class);
		

		String result = emailService.sendTextWithAttachmentEmail(mainBody,file);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public void setCheck(CurrentUserSession cs) {
		this.check = cs;
	}
}
