package com.jivko.ls.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jivko.ls.models.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	public List<Post> findByTitle(String title);
}
