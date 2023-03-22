package com.XcitEducationFoundations.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.XcitEducationFoundations.Entity.CurrentUserSession;
import com.XcitEducationFoundations.Entity.UserDTO;
import com.XcitEducationFoundations.Exception.UserException;
import com.XcitEducationFoundations.Service.LogInService;

@CrossOrigin("*")
@RestController
public class LogInLogOutController {

	@Autowired
	private LogInService logService;

	@PostMapping("/signUp")
	public ResponseEntity<?> SignInHandler(@Valid @RequestBody UserDTO dto) throws UserException {
		Boolean result = logService.signUpUser(dto);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> logInHandler(@Valid @RequestBody UserDTO dto) throws UserException {
		CurrentUserSession result = logService.loginUser(dto);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/logout")
	public ResponseEntity<?> logoutuserHandler(@RequestParam String uuid) throws UserException {

		Boolean log = logService.signOutUser(uuid);

		return new ResponseEntity<>(log, HttpStatus.OK);
	}

}