package com.lchalela.disnet.api.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lchalela.disnet.api.models.entity.Movie;
import com.lchalela.disnet.api.models.records.MovieRecord;

@Repository
public interface IMovieRepository extends CrudRepository<Movie, Long> {

	@Query("SELECT m FROM Movie AS m WHERE m.name =?1")
	public Movie getMovieByName(String name);
	
	@Query("SELECT m FROM Movie AS m WHERE m.id =?1")
	public Movie getMovieById(Long id);
	
	@Query("SELECT m FROM Movie AS m ORDER BY m.createAt ASC")
	public List<Movie> listMovieOrderASC();
	
	@Query("SELECT m FROM Movie AS m ORDER BY m.createAt DESC")
	public List<Movie> listMovieOrderDESC();
	
	@Query("SELECT m.image, m.title, m.createAt FROM Movie AS m")
	public List<MovieRecord> getAllMovies();
	
}
