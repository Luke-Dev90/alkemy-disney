package com.lchalela.disnet.api.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
	Map<String,Object> response = new HashMap<>();

	
	@GetMapping
	public ResponseEntity<List<MovieRecord>> getAllMovies() {
		List<MovieRecord> movies = this.movieService.getAllMovies();
		return new ResponseEntity<List<MovieRecord>>(movies, HttpStatus.OK);
	}
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value="",params = "name",method = RequestMethod.GET)
	public ResponseEntity<?> getMovieByName(@RequestParam(name="name") String name){
		Movie  movie = this.movieService.getMovieByName(name);	
			return new ResponseEntity<>(movie,HttpStatus.OK);
		}
			
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value="",params ="genre",method = RequestMethod.GET)
	public ResponseEntity<?> getMovieByGenderId(@RequestParam(name="genre") String genre) {
		
		List<Movie> movies = Optional.of(this.movieService.getMoviesByIdGender(Long.parseLong(genre)))
					.orElseThrow(()-> new NumberFormatException());
	
		return new ResponseEntity<>(movies, HttpStatus.NOT_FOUND);	
	}
	
	@Secured({"ROLE_USER"})
	@RequestMapping(value="" , params="order", method=RequestMethod.GET)
	public ResponseEntity<?> getAllMoviesOrder(@RequestParam(name="order") String order) throws DataAccessException {
		List<Movie> movies = this.movieService.listMovieOrder(order);
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}
	
	@Secured({"ROLE_USER"})
	@GetMapping("/details/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable String id){	
		
		Movie movie = Optional.of(this.movieService.getMovieById(Long.parseLong(id)))
					.orElseThrow(()-> new NumberFormatException());
		
		return new ResponseEntity<>(movie,HttpStatus.OK);
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping("/save")
	public ResponseEntity<?> saveMovie(@Valid @RequestBody Movie movie){
		
		Movie movieNew = null;	
			
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
	
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable String id,@Valid @RequestBody Movie movie){
		Movie movieResult = null;
				
		try {
			movieResult = Optional.of(this.movieService.updateMovie(Long.parseLong(id), movie))
					.orElseThrow( ()-> new NumberFormatException() );	
		}catch (DataAccessException e) {
			response.put("mesagge", "Error the movie not updated");
			response.put("error", e.getLocalizedMessage());
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
			response.put("message", "The movie updated successfully");
			response.put("movie", movieResult);		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delMovieById(@PathVariable String id){
		this.movieService.deleteMovie( Optional.of(Long.parseLong(id)).orElseThrow( () -> new NumberFormatException()));		
		response.put("message", "The movie deleted successfully");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
}
