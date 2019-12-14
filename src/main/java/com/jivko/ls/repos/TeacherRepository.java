package com.jivko.ls.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jivko.ls.models.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
	
	Teacher findByUsernameAndSubject(final String username, final String subject);
	Teacher findBySubject(final String subject);

}
