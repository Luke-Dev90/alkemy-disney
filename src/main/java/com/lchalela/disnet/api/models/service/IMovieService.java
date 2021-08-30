package com.lchalela.disnet.api.models.service;

import java.util.List;

import com.lchalela.disnet.api.models.entity.Movie;
import com.lchalela.disnet.api.models.records.MovieRecord;

public interface IMovieService {
	
	public List<MovieRecord> getAllMovies();

	public List<Movie> listMovieOrder(String order);
	
	public Movie getMovieByName(String name);
	
	public Movie getMovieById(Long id);
	
	public Movie saveMovie(Movie movie);
	
	public void deleteMovie(Long id);
	
	public List<Movie> getMoviesByIdGender(Long id);
	
	public Movie updateMovie(Long id, Movie movie);
	
}
