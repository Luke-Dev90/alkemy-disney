package com.lchalela.disnet.api.models.service;

import com.lchalela.disnet.api.models.entity.User;

public interface IUserService {
	
	public User saveUser(User user);
	
	public void deleteUser(Long id);
	
}
