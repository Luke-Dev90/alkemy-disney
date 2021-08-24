package com.lchalela.disnet.api.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lchalela.disnet.api.models.entity.User;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserService userService;
	
	
	@Override
	public User saveUser(User user) {
		return this.userService.saveUser(user);
	}

	@Override
	public void deleteUser(Long id) {
		this.userService.deleteUser(id);
	}

}
