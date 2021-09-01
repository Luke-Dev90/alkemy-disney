package com.lchalela.disnet.api.models.exception;

public class NotMovieByNameException extends RuntimeException{


	private static final long serialVersionUID = 4120048948467510029L;

	public NotMovieByNameException(String message) {
		super(message);
	}
	
}
