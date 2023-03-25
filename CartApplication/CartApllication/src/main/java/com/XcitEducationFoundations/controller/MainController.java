package com.XcitEducationFoundations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.XcitEducationFoundations.Exceptions.NoSuchProductException;
import com.XcitEducationFoundations.service.CartServiceImpl;
@CrossOrigin("*")
@RestController
public class MainController {

	@Autowired
	private CartServiceImpl cart;
	
	@GetMapping("/getProduct")
	public ResponseEntity<?> getDataHandler() throws NoSuchProductException {
		return new ResponseEntity<>(cart.sendProductsData(), HttpStatus.OK);
	}
}
