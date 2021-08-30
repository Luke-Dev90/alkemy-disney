package com.lchalela.disnet.api.models.exception;

public class NotCharacterByName extends RuntimeException{

	private static final long serialVersionUID = -5442200863345891794L;

	public NotCharacterByName(String message) {
		super(message);
	}
}
