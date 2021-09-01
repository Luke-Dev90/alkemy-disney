package com.lchalela.disnet.api.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lchalela.disnet.api.models.entity.Movie;
import com.lchalela.disnet.api.models.records.MovieRecord;

@Repository
public interface IMovieRepository extends CrudRepository<Movie, Long> {

	@Query("SELECT m FROM Movie m WHERE m.title =?1")
	public Movie getMovieByName(String name);
	
	@Query("SELECT m FROM Movie m WHERE m.id =?1")
	public Movie getMovieById(Long id);
	
	@Query("SELECT m FROM Movie m ORDER BY m.createAt ASC")
	public List<Movie> listMovieOrderASC();
	
	@Query("SELECT m FROM Movie m ORDER BY m.createAt DESC")
	public List<Movie> listMovieOrderDESC();
	
	@Query(value="SELECT create_at, title, image FROM movies",nativeQuery = true )
	public List<MovieRecord> getAllMovies();
	
	
	@Query("SELECT m FROM Movie m WHERE m.gender.id =?1")
	public List<Movie> getMoviesByIdGender(Long id);
	
}
