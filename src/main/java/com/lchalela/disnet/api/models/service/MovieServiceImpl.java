package com.lchalela.disnet.api.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lchalela.disnet.api.models.entity.Movie;
import com.lchalela.disnet.api.models.records.MovieRecord;
import com.lchalela.disnet.api.models.repository.IMovieRepository;

@Service
public class MovieServiceImpl implements IMovieService{
	
	@Autowired
	private IMovieRepository movieRepository;

	@Override
	public List<MovieRecord> getAllMovies() {
		return movieRepository.getAllMovies();
	}

	@Override
	public List<Movie> listMovieOrder(String order) {
		
		if(order.equalsIgnoreCase("asc")) {
			return this.movieRepository.listMovieOrderASC();
		}else if(order.equalsIgnoreCase("desc")){
			return this.movieRepository.listMovieOrderDESC();
		}
		return null;
	}

	@Override
	public Movie getMovieByName(String name) {
		return this.movieRepository.getMovieByName(name);
	}

	@Override
	public Movie getMovieById(Long id) {
		return this.movieRepository.getMovieById(id);
	}

	@Override
	public Movie saveMovie(Movie movie) {
		return this.movieRepository.save(movie);
	}

	@Override
	public void deleteMovie(Long id) {
		this.movieRepository.deleteById(id);
	}

	@Override
	public List<Movie> getMoviesByIdGender(Long id) {
		return this.movieRepository.getMoviesByIdGender(id);
	}
	
}
