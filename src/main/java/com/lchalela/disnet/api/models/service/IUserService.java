package com.lchalela.disnet.api.models.service;

import java.io.IOException;



import com.lchalela.disnet.api.models.entity.Users;

public interface IUserService {
	
	public Users findByUsername(String username);
	
	public Users saveUser(Users user);
	
	public void sendWelcomeEmail(String email) throws IOException;
	
	
}
