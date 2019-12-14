package com.jivko.ls.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jivko.ls.models.User;
import com.jivko.ls.repos.UserRepository;

@RestController
public class UserLoginRest {

	private UserRepository repository;
	//private UserDetailsService userdetailsService;
	

	@Autowired	
	public UserLoginRest(final UserRepository repository) {
		this.repository = repository;
	}
	
	
	@PostMapping(value="/login")
	public ResponseEntity<String> login(@RequestParam(name="username") String username,
			@RequestParam(name="subject") String subject, HttpSession session){
		final User currentUser = repository.findByUsernameAndSubject(username, subject);
		if(null != currentUser) {
			session.setAttribute("currentUser", currentUser);
		}return ResponseEntity.status(HttpStatus.OK)
				.body("profile.html");
		
	}

	public UserRepository getUserRepository() {
		return repository;
	}


	public void setUserRepository(UserRepository userRepository) {
		this.repository = userRepository;
	}
}
