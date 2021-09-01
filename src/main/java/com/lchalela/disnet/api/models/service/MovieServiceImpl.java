package com.lchalela.disnet.api.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lchalela.disnet.api.models.entity.Movie;
import com.lchalela.disnet.api.models.exception.ListMoviesException;
import com.lchalela.disnet.api.models.exception.NotDetailsID;
import com.lchalela.disnet.api.models.exception.NotMovieByGenreID;
import com.lchalela.disnet.api.models.exception.NotMovieByNameException;
import com.lchalela.disnet.api.models.exception.NotMovieID;
import com.lchalela.disnet.api.models.exception.NotMovieOrderException;
import com.lchalela.disnet.api.models.records.MovieRecord;
import com.lchalela.disnet.api.models.repository.IMovieRepository;

@Service
public class MovieServiceImpl implements IMovieService {

	@Autowired
	private IMovieRepository movieRepository;

	@Override
	@Transactional(readOnly =true)
	public List<MovieRecord> getAllMovies() {
		
		List<MovieRecord> list = movieRepository.getAllMovies();
		
		if(list.isEmpty()) {			
			throw new ListMoviesException("List not found");
		}else {
			return list;
		}
		
	}

	@Override
	@Transactional(readOnly =true)
	public List<Movie> listMovieOrder(String order) {
		List<Movie> movies = null;
		
		if (order.equalsIgnoreCase("asc")) {
			movies = this.movieRepository.listMovieOrderASC();
			
			
		} else if (order.equalsIgnoreCase("desc")) {
			 movies = this.movieRepository.listMovieOrderDESC();
		}else {
			throw new NotMovieOrderException("Ordenamiento invalido");
		}
		
		if(movies.isEmpty()) {
			throw new ListMoviesException("List not found");
		}
		
		return movies;
	}

	
	@Override
	@Transactional(readOnly =true)
	public Movie getMovieByName(String name) {
		
		Movie movie = this.movieRepository.getMovieByName(name); 
	
		if(movie == null) {
			throw new NotMovieByNameException("Movie by name not found");
		}else {
			return movie;
		}
	}

	@Override
	@Transactional(readOnly =true)
	public Movie getMovieById(Long id) {
		return this.movieRepository.findById(id)
				.orElseThrow( ()-> new NotDetailsID("Not found movie by id: " + id));
	}

	@Override
	@Transactional
	public Movie saveMovie(Movie movie) {
		return this.movieRepository.save(movie);
	}


	@Override
	@Transactional(readOnly =true)
	public List<Movie> getMoviesByIdGender(Long id) {
		List<Movie> movies = this.movieRepository.getMoviesByIdGender(id);		
		if(movies.isEmpty()) {
			throw new NotMovieByGenreID("Not found genre id");
		}else {
			return movies;
		}	 
	}

	@Override
	@Transactional
	public void deleteMovie(Long id) {
		
		// CREATE EXCEPTION NOT MOVIE ID	
		Movie movieResult = this.movieRepository.getMovieById(id);
		
		if(movieResult != null) {
			movieResult.removePersonajes();
			this.movieRepository.delete(movieResult);
		}else {
			throw new NotMovieID("Not found id movie deleted");
		}
	}

	@Override
	@Transactional
	public Movie updateMovie(Long id, Movie movie) {
		
		Optional<Movie> movieResult = Optional.of(this.movieRepository.getMovieById(id));
		Movie movieUpdate = null;
		
		if(movieResult.isPresent()) {
			movieUpdate = movieResult.get();
			movieUpdate.setCharacters(movie.getCharacters());
			movieUpdate.setCreateAt(movie.getCreateAt());
			movieUpdate.setGender(movie.getGender());
			movieUpdate.setImage(movie.getImage());
			movieUpdate.setQualification(movie.getQualification());
			movieUpdate.setTitle(movie.getImage());
			
			movieUpdate = this.movieRepository.save(movieUpdate);
		}else {
			throw new NotMovieID("Not found movie id");
		}	
		
		return movieUpdate;
	}
}
