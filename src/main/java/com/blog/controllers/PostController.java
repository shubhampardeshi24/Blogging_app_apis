package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.PostDTO;
import com.blog.payloads.PostResponse;
import com.blog.services.PostService;

@RestController
@RequestMapping("/apis/posts")
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

	// get all posts
	@GetMapping("/")
	public ResponseEntity<PostResponse> gelAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postID", required = false) String sortBy,
			@RequestParam (value = "sortDirection", defaultValue = "asc", required = false) String sortDirection ) {

		PostResponse allPost = service.getAllPost(pageNumber, pageSize, sortBy, sortDirection);
		return new ResponseEntity<PostResponse>(allPost, HttpStatus.OK);
	}

	// get post by id
	@GetMapping("/{postID}")
	public ResponseEntity<PostDTO> getPostByID(@PathVariable Integer postID) {
		PostDTO postById = service.getPostById(postID);
		return new ResponseEntity<PostDTO>(postById, HttpStatus.OK);
	}

	// Delete post
	@DeleteMapping("/{postID}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postID) {
		service.deletePost(postID);
		ApiResponse response = new ApiResponse("Post Deleted Successfully", true);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	@PutMapping("/{postID}")
	public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO dto, @PathVariable Integer postID) {
		PostDTO updatePost = service.updatePost(dto, postID);
		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);
	}
	
	@GetMapping("/search/{keywords}")
	public ResponseEntity<List<PostDTO>> searchPostByTitle(@PathVariable String keywords) {
		List<PostDTO> searchPosts = service.searchPosts(keywords);
		return new ResponseEntity<List<PostDTO>>(searchPosts, HttpStatus.OK);
	}
}
