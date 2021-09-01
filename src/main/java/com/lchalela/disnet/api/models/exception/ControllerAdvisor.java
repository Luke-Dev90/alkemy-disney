package com.lchalela.disnet.api.models.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
	Map<String,Object> body = new HashMap<>();
	
	
	//EXCEPTIONS CHARACTER
	
	@ExceptionHandler(ListCharacterException.class)
	public ResponseEntity<Object> handledListCharacter(DataAccessException ex,WebRequest request){
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Character list not found");
		return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(NotCharacterByName.class)
	public ResponseEntity<Object>notCharacterByName(NotCharacterByName ex,WebRequest request){
		body.put("timestamp", LocalDateTime.now());
		body.put("message","The name not found " + ex.getLocalizedMessage() );
		return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(NotCharacterAge.class)
	public ResponseEntity<Object> notCharacterAge(NotCharacterAge ex,WebRequest request){
		body.put("timestamp", LocalDateTime.now());
		body.put("message","The age not found " + ex.getLocalizedMessage() );
		return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NotCharacterByMovieID.class)
	public ResponseEntity<Object> notCharacterByMovieID(NotCharacterByMovieID ex, WebRequest request){
		body.put("timestamp", LocalDateTime.now());
		body.put("message", " Not found the movie id");
		return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(NotCharacterID.class)
	public ResponseEntity<Object> handledCharacterIdNotFoundException(NotCharacterID ex,WebRequest request) {
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Character not found");
		return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NotCreateCharacter.class)
	public ResponseEntity<Object> notCreatedCharacter(NotCreateCharacter ex, WebRequest request){
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "#ERROR: Character not created" );
		return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<Object> dataAccessException(ErrorDeletedException ex,WebRequest request){
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Error the id not found " + ex.getLocalizedMessage() );
		return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// EXCEPTIONS MOVIES
	
	@ExceptionHandler(ListMoviesException.class)
	public ResponseEntity<Object> listMovieException(ListMoviesException ex, WebRequest request){
		body.put("timestamp", LocalDateTime.now());
		body.put("message","Movie list not found");
		return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NotMovieByNameException.class)
	public ResponseEntity<Object> notMovieByName(NotMovieByNameException ex , WebRequest request){
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Movie by name not found: " + ex.getLocalizedMessage());
		return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NotMovieByGenreID.class)
	public ResponseEntity<Object> notMovieByGender(NotMovieByGenreID ex, WebRequest request){
		body.put("timestamp", LocalDateTime.now());
		body.put("message","Movie by genreID not found " + ex.getLocalizedMessage());
		return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NotMovieOrderException.class)
	public ResponseEntity<Object> notMovieOrderException(NotMovieOrderException ex, WebRequest request){
		body.put("timestamp",LocalDateTime.now());
		body.put("message", "The sort is invalid format.");
		return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotMovieID.class)
	public ResponseEntity<Object> notMovieID(NotMovieID ex,WebRequest request){
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Movie not deleted, not found id: " + ex.getLocalizedMessage());
		return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NotDetailsID.class)
	public ResponseEntity<Object> notDetailID(NotDetailsID es,WebRequest request){
		body.put("timestamp",LocalDateTime.now());
		body.put("message","Movie not found by id");
		return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
	}
	
	// EXCEPTION USER REGISTER
	
	@ExceptionHandler(UserOrEmailException.class)
	public ResponseEntity<Object> existUserOrEmail(UserOrEmailException ex, WebRequest request){
		body.put("timestamp",LocalDateTime.now());
		body.put("message","Username or Email exist");
		return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
	}
	
	// COMMONS EXCEPTIONS
	
	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<Object> numberFormatException(NumberFormatException ex,WebRequest request){
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "The format exepected is number and you send string " + ex.getLocalizedMessage());
		return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers,HttpStatus status, WebRequest request){
		
			body.put("timestamp",LocalDateTime.now());
			body.put("status", status.value() );
		
			List<String>errors = ex.getFieldErrors()
					.stream()
					.map( err -> "Field error: " + err.getField() + " " + err.getDefaultMessage() + " desd ex control")
					.collect(Collectors.toList());
			body.put("errors", errors);
		
			return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);
	}
}
