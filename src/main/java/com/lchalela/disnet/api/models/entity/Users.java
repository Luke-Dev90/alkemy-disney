package com.lchalela.disnet.api.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="users")
public class Users implements Serializable{
	
	private static final long serialVersionUID = -8632088867562009667L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, length = 20)
	private String username;
	
	@Column(length = 70)
	private String password;
	
	private Boolean enabled;
	
	@Column(unique = true)
	private String email;
	
	@JsonIgnoreProperties("users")
	@ManyToMany(fetch = FetchType.EAGER, cascade= {  CascadeType.MERGE,
			CascadeType.REFRESH })
	@JoinTable(name="users_roles",
	joinColumns=@JoinColumn(name="user_id")
	,inverseJoinColumns=@JoinColumn(name="role_id"))
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	private List<Role> roles = new ArrayList<Role>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public void addMovie(Role role) {
		this.roles.add(role);
	}
	
}
