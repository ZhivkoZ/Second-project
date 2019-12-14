package com.jivko.ls.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jivko.ls.models.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	Student findByNameAndSubject(final String username, final String subject);
	Student findByName(final String username);

}
