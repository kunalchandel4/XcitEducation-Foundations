package com.XcitEducationFoundations.Exception;

import java.time.LocalDateTime;

import javax.mail.MessagingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.XcitEducationFoundations.Entity.MyEmailError;

@ControllerAdvice

public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyEmailError> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e,
			WebRequest req) {

		MyEmailError err = new MyEmailError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setDescription(e.getFieldError().toString());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyEmailError> myExceptionHandler(Exception e, WebRequest req) {

		MyEmailError err = new MyEmailError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyEmailError> noHandlerFoundHandler(NoHandlerFoundException e, WebRequest req) {

		MyEmailError err = new MyEmailError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(MessagingException.class)
	public ResponseEntity<MyEmailError> noHandlerFoundHandler(MessagingException e, WebRequest req) {

		MyEmailError err = new MyEmailError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(e.getMessage());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyEmailError> myUserHandler(UserException ue, WebRequest req) {

		MyEmailError err = new MyEmailError();
		err.setTimestamp(LocalDateTime.now());
		err.setMessage(ue.getMessage());
		err.setPath(req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

}
