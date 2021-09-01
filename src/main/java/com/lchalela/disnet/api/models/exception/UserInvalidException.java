package com.lchalela.disnet.api.models.exception;

public class UserInvalidException extends RuntimeException{

	private static final long serialVersionUID = 4528090803739877235L;

	
	public UserInvalidException(String message) {
		super(message);
	}
}
