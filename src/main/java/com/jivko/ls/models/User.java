package com.jivko.ls.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="user")
@JsonIgnoreProperties({"posts", "password"})
public abstract class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username = "";
	private String password = "";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private List<Post> posts;
	
	public User(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public List<Post> getPosts() {
		if (null == posts) {
			posts = new ArrayList<>();
		}
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public void addPost(Post post) {
		getPosts().add(post);
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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "id"))
	private Set<Role> roles;
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}

