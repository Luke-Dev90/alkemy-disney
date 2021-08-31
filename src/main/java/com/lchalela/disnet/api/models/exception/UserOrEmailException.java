package com.lchalela.disnet.api.models.exception;

public class UserOrEmailException extends RuntimeException{


	private static final long serialVersionUID = 5935263821867949197L;

	public UserOrEmailException(String message) {
		super(message);
	}
}
