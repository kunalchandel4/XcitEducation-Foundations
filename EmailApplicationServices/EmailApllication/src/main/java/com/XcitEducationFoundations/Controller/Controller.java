package com.XcitEducationFoundations.Controller;

import javax.mail.MessagingException;
import javax.security.auth.login.LoginException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.XcitEducationFoundations.Entity.CurrentUserSession;
import com.XcitEducationFoundations.Entity.EmailBody;
import com.XcitEducationFoundations.Exception.UserException;
import com.XcitEducationFoundations.Service.EmailService;
import com.XcitEducationFoundations.Service.LogInService;

@RestController
public class Controller {

	@Autowired
	private EmailService emailService;

	@Autowired
	private LogInService logService;

	private CurrentUserSession check;

	@PostMapping("/sendEmail")
	public ResponseEntity<?> SendEmailHandler(@Valid @RequestBody EmailBody body, @RequestParam String uuid)
			throws UserException, MessagingException, LoginException {
		String result = emailService.sendTextToEmail(body);
		this.setCheck(logService.getSessionByUuid(uuid));
		if (check == null)
			throw new LoginException("This user is not logged In");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	public void setCheck(CurrentUserSession cs) {
		this.check = cs;
	}
}
