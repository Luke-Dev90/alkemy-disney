package com.lchalela.disnet.api.models.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lchalela.disnet.api.models.entity.Users;
import com.lchalela.disnet.api.models.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService,UserDetailsService{

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private IUserRepository userService;
	
	
	@Override
	public Users saveUser(Users user) {
		return this.userService.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		this.userService.deleteById(id);
	}
	
	@Override
	public void sendWelcomeEmail(String email) throws IOException{
		
	}

	@Override
	@Transactional(readOnly=true)
	public Users findByUsername(String username) {
		return this.userService.findByUsername(username);
	}

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = this.userService.findByUsername(username);
		
		if(user == null) {
			logger.error("Error login, the user or password is invalid");
			throw new UsernameNotFoundException("Error login, the user or password is invalid");
		}
		
		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRole()))
				.collect(Collectors.toList());
		
		return new User(user.getUsername(), user.getPassword(),user.getEnabled(),true,true,true,authorities);
	}

}
