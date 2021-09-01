package com.lchalela.disnet.api.models.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lchalela.disnet.api.models.entity.Users;
import com.lchalela.disnet.api.models.exception.UserOrEmailException;
import com.lchalela.disnet.api.models.repository.IUserRepository;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class UserServiceImpl implements IUserService , UserDetailsService {
	
	private final String KEY = "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";
	
	@Autowired
	private IUserRepository userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public void saveUser(Users user) {
		Users userresult = null;
	
		userresult =  this.userService.findByUsernameOrEmail(user.getUsername(),user.getEmail());
		
		if(userresult == null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			this.userService.save(user);		
			
		}else {
			throw new UserOrEmailException("Username or email exist");
		}
			
	}

	@Override
	public void sendWelcomeEmail(String email) throws IOException{
		Email from = new Email("lukee_sf@hotmail.com");
		String subject = "Welcome";
		Email to = new Email(email);
		Content content = new Content("text/plain", "Welcome to Api Disney");
		Mail mail = new Mail(from, subject, to, content);
		
		SendGrid sg = new SendGrid(KEY);
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			throw ex;
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Users findByUsername(String username) {
		return this.userService.findByUsername(username);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			Users user = this.findByUsername(username);
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
					new ArrayList<>());
	}



}
