package com.lchalela.disnet.api.models.exception;

public class NotMovieByGenreID extends RuntimeException{

	private static final long serialVersionUID = -4885677654219704729L;

	public NotMovieByGenreID(String message) {
		super(message);
	}
}
