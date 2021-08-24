package com.lchalela.disnet.api.models.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="characters")
public class Character {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(min = 1, max=60)
	private String name;
	
	@NotNull
	private Long age;
	
	@NotNull
	private Double weight;
	
	@NotEmpty
	private String history;
	
	@JsonIgnoreProperties("characters")
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name="character_movie",
		joinColumns = @JoinColumn(name="character_id"),
		inverseJoinColumns=@JoinColumn(name="movie_id"))
	private List<Movie> movies;
}
