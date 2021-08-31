package com.lchalela.disnet.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lchalela.disnet.api.models.entity.Users;
import com.lchalela.disnet.api.models.service.IUserService;

@RestController
@RequestMapping("/auth")
public class UserRestController {

	@Autowired
	private IUserService  userservice;
	private Map<String,Object> response = new HashMap<>();
	
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody Users user){		
		this.userservice.saveUser(user);
		response.put("message", "User created successfull");
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	
}
