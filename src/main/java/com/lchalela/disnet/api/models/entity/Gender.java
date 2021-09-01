package com.lchalela.disnet.api.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="genders")
public class Gender implements Serializable {
	

	private static final long serialVersionUID = -4702850969817534361L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "name required")
	private String name;
	
	@NotEmpty(message = "imgen url required")
	private String image;
	

	@JsonBackReference
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH},
				mappedBy = "gender")
	private List<Movie> movies;
	
	public Gender() {
		
	}

	public Gender(Long id,  String name,  String image, List<Movie> movies) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.movies = movies;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	
	
}
