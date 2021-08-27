package com.lchalela.disnet.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

	@GetMapping("/genre")
	@ResponseBody
	public ResponseEntity<?> getMovieByGenderId(@RequestParam String id) {

		List<Movie> movies = null;
		Map<String, Object> response = new HashMap<>();

		try {

			movies = this.movieService.getMoviesByIdGender(Long.parseLong(id));
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);

		} catch (NumberFormatException e) {

			response.put("mensaje", "Error no ingreso un id correcto");
			response.put("error", e.getLocalizedMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/order")
	@ResponseBody
	public ResponseEntity<?> getAllMoviesOrder(@RequestParam String order) throws DataAccessException {
		Map<String, Object> response = new HashMap<>();
		List<Movie> movies;

		movies = this.movieService.listMovieOrder(order);

		if (movies == null) {
			response.put("mensaje", "Ingreso un ordenamiento incorrecto");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}

}
