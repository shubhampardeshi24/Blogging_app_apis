package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.PostDTO;
import com.blog.services.PostService;

@RestController
@RequestMapping("/apis/")
public class PostController {

	@Autowired
	private PostService service;

	@PostMapping("/user/{userID}/category/{categoryID}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userID,
			@PathVariable Integer categoryID) {
		PostDTO createPost = service.createPost(postDTO, userID, categoryID);

		return new ResponseEntity<PostDTO>(createPost, HttpStatus.CREATED);
	}

	// get By user
	@GetMapping("/user/{userID}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userID) {
		List<PostDTO> postsByUser = service.getPostsByUser(userID);

		return new ResponseEntity<List<PostDTO>>(postsByUser, HttpStatus.OK);
	}

	// get By Category
	@GetMapping("/category/{categoryID}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer categoryID) {
		List<PostDTO> postsByCategory = service.getPostsByCategory(categoryID);

		return new ResponseEntity<List<PostDTO>>(postsByCategory, HttpStatus.OK);
	}
}
