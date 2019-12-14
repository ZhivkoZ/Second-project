package com.jivko.ls.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jivko.ls.models.Post;
import com.jivko.ls.models.User;
import com.jivko.ls.repos.PostRepository;
import com.jivko.ls.repos.UserRepository;

@RestController
public class PostManagerRest {
	
	private PostRepository postRepo;
	private UserRepository userRepo;
	
	
	@Autowired
	public PostManagerRest(PostRepository postRepo, UserRepository userRepo) {
		this.postRepo = postRepo;
		this.userRepo = userRepo;
	}
	
	@PostMapping("/removeMyPost")
	public ResponseEntity<String> removePost(@RequestParam(name = "id") int id, HttpSession session) {
		final User user = (User) session.getAttribute("currentUser");
		if (null == user) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
													.body("");
		}
		final Post postForRemove = postRepo.findById(id).orElse(null);
		if (null != postForRemove) {
			if (!user.equals(postForRemove.getOwner())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			} else {
				user.getPosts().remove(postForRemove);
				postRepo.delete(postForRemove);
				session.setAttribute("currentUser", userRepo.save(user));
			}
		}
		return ResponseEntity.ok().body("Post with id: " + id 
										+ " is removed");
	}
	
	@GetMapping("/getPosts")
	public ResponseEntity<List<Post>> getAllPosts(HttpSession session) {
		final List<Post> posts = new ArrayList<>();
		final String title = (String) session.getAttribute("title");
		if (null == title) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(posts);
		} else {
			posts.addAll(postRepo.findByTitle(title));
		}
		return ResponseEntity.ok(posts);
	}

	@PostMapping("/createPost")
	public ResponseEntity<Post> createPost(
			@RequestParam(name = "title") String title,
			@RequestParam(name = "comment") String comment,
			HttpSession session) {

		final User user = (User) session.getAttribute("currentUser");
		if (null == user) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		final Post post = new Post(title, comment);
		post.setOwner(user);
		user.addPost(post);
		session.setAttribute("currentUser", userRepo.save(user));

		return ResponseEntity.ok(post);
	}

	public PostRepository getPostRepo() {
		return postRepo;
	}

	public void setPostRepo(PostRepository postRepo) {
		this.postRepo = postRepo;
	}
	
	

}
