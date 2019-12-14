package com.jivko.ls.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity(name="Teacher")
@JsonIgnoreProperties("password")
public class Teacher extends User{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String username;
	private String password;
	@Column(name="subject", nullable=false, unique=false)	
	private String subject;
	@Column(name="email",nullable=false, unique=true )
	private String email;
	@OneToMany(mappedBy="owner", fetch = FetchType.EAGER,
			cascade = CascadeType.ALL)
	private Set<Subject> subjects;
	
	public Teacher(int id, String email, String username, String password) {
		super(username, password);
		this.email = email;
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
	
	/*
	 * @ManyToMany(fetch = FetchType.EAGER)
	 * 
	 * @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
	 * inverseJoinColumns = @JoinColumn(name = "id")) private Set<Role> roles;
	 */

}
