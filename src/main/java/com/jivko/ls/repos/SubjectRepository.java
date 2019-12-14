package com.jivko.ls.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jivko.ls.models.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
	
	Subject findByName(final String subjectName);

}
