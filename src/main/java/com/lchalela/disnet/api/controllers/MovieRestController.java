package com.lchalela.disnet.api.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lchalela.disnet.api.models.entity.Movie;
import com.lchalela.disnet.api.models.records.MovieRecord;
import com.lchalela.disnet.api.models.service.IMovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieRestController {

	@Autowired
	private IMovieService movieService;

	@GetMapping
	public ResponseEntity<List<MovieRecord>> getAllMovies() {
		List<MovieRecord> movies = this.movieService.getAllMovies();
		return new ResponseEntity<List<MovieRecord>>(movies, HttpStatus.OK);
	}
	
	@RequestMapping(value="",params = "name",method = RequestMethod.GET)
	public ResponseEntity<?> getMovieByName(@RequestParam(name="name") String name){

		Map<String, Object> response = new HashMap<>();
		
		Movie  movie = this.movieService.getMovieByName(name);
		if(movie == null) {
			response.put("message", "The movie not found: " + name);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
		}else {			
			return new ResponseEntity<>(movie,HttpStatus.OK);
		}
		
		
	}

	@RequestMapping(value="",params ="genre",method = RequestMethod.GET)
	public ResponseEntity<?> getMovieByGenderId(@RequestParam(name="genre") String genre) {
		List<Movie> movies = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			movies = this.movieService.getMoviesByIdGender(Long.parseLong(genre));
			
		} catch (NumberFormatException e) {
			response.put("message", "The id have an incorrect format: " + genre );
			response.put("error", e.getLocalizedMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(!movies.isEmpty()) {
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
		}else {				
			response.put("message", "The Gender id NOT FOUND: " + genre );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);	
		}
	}
	

	@RequestMapping(value="" , params="order", method=RequestMethod.GET)
	public ResponseEntity<?> getAllMoviesOrder(@RequestParam(name="order") String order) throws DataAccessException {
		Map<String, Object> response = new HashMap<>();
		List<Movie> movies;

		movies = this.movieService.listMovieOrder(order);

		if (movies == null) {
			response.put("message", "You entered an incorrect sort order");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}
	
	@GetMapping("/details/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable String id){
		
		Map<String,Object> response = new HashMap<>();
		Movie movie = null;
		
		try {		
			movie = this.movieService.getMovieById(Long.parseLong(id));
			 
			if(movie == null) {		
					response.put("message", "The movie not found");
					return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
				}
			
		}catch(NumberFormatException nfe) {
			
			response.put("message", "The format ID is incorrect: " + id);
			response.put("error", nfe.getLocalizedMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		return new ResponseEntity<>(movie,HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<?> saveMovie(@Valid @RequestBody Movie movie,BindingResult result){
		
		Movie movieNew = null;
		
		Map<String,Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String>errors = result.getFieldErrors()
					.stream()
					.map(err -> "The fild: " + err.getField() + " " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
		}
		
		try {
			movieNew =	this.movieService.saveMovie(movie);
		}catch(DataAccessException e) {
			response.put("message", "#ERROR: MOVIE NOT CREATED");
			response.put("error", e.getLocalizedMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

			response.put("message","The movie save in database " + movie.getTitle());
			response.put("movie", movieNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable String id,@Valid @RequestBody Movie movie,BindingResult result){
		Movie movieResult = null;
		Movie movieUpdate = null;
		Map<String,Object> response = new HashMap<>();
		
		try {
			movieResult = this.movieService.getMovieById( Long.parseLong(id));	
		}catch(NumberFormatException nf) {
			response.put("error", "The format ID is incorrect: " + id);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(movie == null) {
			response.put("error", "Not found movie with id: " + id);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "The Field " + err.getField() + " " + err.getDefaultMessage() )
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		try {
			movieResult.setTitle( movie.getTitle() );
			movieResult.setGender( movie.getGender() );
			movieResult.setImage( movie.getImage() );
			movieResult.setQualification(movie.getQualification());
			movieResult.setCharacters( movie.getCharacters());
			movieResult.setCreateAt( movie.getCreateAt());
			
			movieUpdate = this.movieService.saveMovie(movieResult);
			
		}catch (DataAccessException e) {
			response.put("mesagge", "Error the movie not updated");
			response.put("error", e.getLocalizedMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
			response.put("message", "The movie updated successfully");
			response.put("movie", this.movieService.saveMovie(movieUpdate));
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable String id){
		Map<String,Object> response = new HashMap<>();
		
		try {
			
			this.movieService.deleteMovie(Long.parseLong(id));
			
		}catch(NumberFormatException nf) {
			response.put("message","Error The format ID is incorrect: " + id);
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(DataAccessException dae) {
			response.put("message","Error not found the movie with id: " + id);
			response.put("error",dae.getLocalizedMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
			response.put("message","The movie deleted successfully");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
}
