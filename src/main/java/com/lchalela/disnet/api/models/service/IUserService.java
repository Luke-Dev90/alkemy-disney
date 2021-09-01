package com.lchalela.disnet.api.models.service;

import java.io.IOException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lchalela.disnet.api.models.entity.Users;

public interface IUserService {
	
	public Users findByUsername(String username);
	
	public void saveUser(Users user);
	
	public void sendWelcomeEmail(String email) throws IOException;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	
}
