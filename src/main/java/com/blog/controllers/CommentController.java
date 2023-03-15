package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CommentDTO;
import com.blog.services.CommentService;

@Controller
@RequestMapping("/api")
public class CommentController {

	
	@Autowired
	private CommentService service;
	
	@PostMapping("/comment/post/{postID}")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO, @PathVariable Integer postID)
	{
		CommentDTO createComment = service.createComment(commentDTO, postID);
		
		return new ResponseEntity<CommentDTO>(createComment, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comment/{commentID}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentID)
	{
		service.deleteComment(commentID);
		
		ApiResponse response = new ApiResponse();
		response.setMessage("commment deleted successfully");
		response.setSuccess(true);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.CREATED);
	}
}
