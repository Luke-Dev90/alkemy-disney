package com.lchalela.disnet.api.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="characters")
public class Character implements Serializable {

	private static final long serialVersionUID = -1033951515159598492L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Name not null and min 1 between 60")
	@Size(min = 1, max=60)
	private String name;
	
	@NotEmpty(message = "imgen url required")
	private String image;
	
	@NotNull(message = "Age required")
	private Long age;
	
	@NotNull(message = "Weight required")
	private Double weight;
	
	@NotEmpty(message = "Historty required")
	private String history;
	
	@JsonIgnoreProperties("characters")
	@ManyToMany(cascade =  { CascadeType.MERGE,
			CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinTable(name="character_movie",
		joinColumns = @JoinColumn(name="character_id"),
		inverseJoinColumns=@JoinColumn(name="movie_id"))
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private List<Movie> movies;
	
	
	public Character(){
		
	}
	
	public Character(Long id, String name,String image, Long age,
			 Double weight, String history, List<Movie> movies) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.age = age;
		this.weight = weight;
		this.history = history;
		this.movies = movies;
	}

	public void addMovie(Movie move) {
		this.movies.add(move);
	}

	public void removeMovie() {
		this.movies.removeAll(movies);
	}


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	
	
}
