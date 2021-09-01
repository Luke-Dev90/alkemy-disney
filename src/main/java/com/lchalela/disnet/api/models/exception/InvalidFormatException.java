package com.lchalela.disnet.api.models.exception;

public class InvalidFormatException extends RuntimeException{

	private static final long serialVersionUID = -3558804448772716370L;
	
	public InvalidFormatException() {
		super("The format exepected is number and you send string");
	}

}
