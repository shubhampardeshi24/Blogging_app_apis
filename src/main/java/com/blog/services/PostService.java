package com.blog.services;

import java.util.List;

import com.blog.entities.Post;
import com.blog.payloads.PostDTO;

public interface PostService {
	
	//create
	PostDTO createPost(PostDTO post, Integer userID, Integer categoryID);
	
	//update
	PostDTO updatePost(PostDTO post);
	
	//delete
	void deletePost(Integer postID);
	
	//get All posts
	List<PostDTO> getAllPost();
	
	//getSingle post
	PostDTO getPostById(Integer postID);
	
	//get All posts by category
	List<PostDTO> getPostsByCategory(Integer categoryID);
	
	//get All posts by user
	List<PostDTO> getPostsByUser(Integer userID);
	
	
	//search post
	List<Post> searchPosts(String keyword); 
	
}
