package com.lchalela.disnet.api.models.exception;

public class NotCharacterID extends RuntimeException {

	private static final long serialVersionUID = 1257911950041240503L;
	
	public NotCharacterID(Long id) {
		super(String.format("Character not found with id: " + id));
	}

}
