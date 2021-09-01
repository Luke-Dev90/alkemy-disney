package com.lchalela.disnet.api.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lchalela.disnet.api.auth.JwtUtil;
import com.lchalela.disnet.api.models.entity.AuthenticationRequest;
import com.lchalela.disnet.api.models.entity.Users;
import com.lchalela.disnet.api.models.service.IUserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth")
public class UserRestController {

	@Autowired
	private IUserService  userservice;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@ApiOperation(value="Register new user")
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@Valid @RequestBody Users user) throws IOException{
		Map<String,Object> response = new HashMap<>();
			this.userservice.saveUser(user);
			this.userservice.sendWelcomeEmail(user.getEmail());
		response.put("message", "User registered successfull");
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@ApiOperation(value="Login users")
	@PostMapping("/login")
	public ResponseEntity<?> generatetoken(@RequestBody AuthenticationRequest authrequest) throws Exception {
		Map<String,Object> response = new HashMap<>();
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authrequest.getUsername(), authrequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("invalid username/password");
		}
		
		response.put("token",jwtUtil.generateToken(authrequest.getUsername()) );
		response.put("message", "Authorizated");
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
}
