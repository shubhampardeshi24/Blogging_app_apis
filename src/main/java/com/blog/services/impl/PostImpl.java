package com.blog.services.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourcesNotFoundException;
import com.blog.payloads.PostDTO;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.PostService;

@Service
public class PostImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDTO createPost(PostDTO post, Integer userID, Integer categoryID) {
		User user = userRepo.findById(userID).orElseThrow(() -> new ResourcesNotFoundException("user", "user ID", userID));
		
		Category category = categoryRepo.findById(categoryID).orElseThrow(() -> new ResourcesNotFoundException("user", "user ID", categoryID));
		Post postToBeAdded = mapper.map(post, Post.class);
		
		postToBeAdded.setImagename("default.png");
		postToBeAdded.setAddedDate(new Date());
		postToBeAdded.setUser(user);
		postToBeAdded.setCategory(category);
		
		Post newPost = postRepo.save(postToBeAdded);
		return mapper.map(newPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO post) {
		
		return null;
	}

	@Override
	public void deletePost(Integer postID) {

		
	}

	@Override
	public List<PostDTO> getAllPost() {
		return null;
	}

	@Override
	public PostDTO getPostById(Integer postID) {
		return null;
	}

	@Override
	public List<Post> getPostsByCategory(Integer categoryID) {
		return null;
	}

	@Override
	public List<Post> getPostsByUser(Integer userID) {
		return null;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		return null;
	}

}
