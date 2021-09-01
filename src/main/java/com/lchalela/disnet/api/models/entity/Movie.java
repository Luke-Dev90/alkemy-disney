package com.lchalela.disnet.api.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="movies")
public class Movie  implements Serializable{
	

	private static final long serialVersionUID = 8803015410245108240L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Title is required")
	@Size(min=1, max=60)
	private String title;
	
	@NotEmpty(message = "Image url is required")
	private String image;
	
	@Column
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date createAt;
	
	
	@Min(1)
	@Max(5)
	private Long qualification;
	

	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="gender_id")
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private Gender gender;
	
	@JsonIgnoreProperties("movies")
	@ManyToMany(mappedBy = "movies",cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH },
				fetch = FetchType.LAZY)

	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private List<Character> characters = new ArrayList<Character>();

	public Movie() {}
	
	public Movie(Long id, String title,  String image, Date createAt,Long qualification, Gender gender, List<Character> characters) {
		this.id = id;
		this.title = title;
		this.image = image;
		this.createAt = createAt;
		this.qualification = qualification;
		this.gender = gender;
		this.characters = characters;
	}

	public void removePersonajes() {
		this.characters.removeAll(characters);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Long getQualification() {
		return qualification;
	}

	public void setQualification(Long qualification) {
		this.qualification = qualification;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public List<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}
	
	
}
