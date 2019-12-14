package com.jivko.ls.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jivko.ls.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUsernameAndSubject(final String username, final String subject);
	User findBySubject(String subject);

}
