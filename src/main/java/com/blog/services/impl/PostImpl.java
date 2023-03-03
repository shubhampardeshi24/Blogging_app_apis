package com.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourcesNotFoundException;
import com.blog.payloads.PostDTO;
import com.blog.payloads.PostResponse;
import com.blog.repositories.CategoryRepo;
import com.blog.repositories.PostRepo;
import com.blog.repositories.UserRepo;
import com.blog.services.PostService;

@Service
public class PostImpl implements PostService {

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
		User user = userRepo.findById(userID)
				.orElseThrow(() -> new ResourcesNotFoundException("user", "user ID", userID));

		Category category = categoryRepo.findById(categoryID)
				.orElseThrow(() -> new ResourcesNotFoundException("category", "category ID", categoryID));
		Post postToBeAdded = mapper.map(post, Post.class);

		postToBeAdded.setImagename("default.png");
		postToBeAdded.setAddedDate(new Date());
		postToBeAdded.setUser(user);
		postToBeAdded.setCategory(category);

		Post newPost = postRepo.save(postToBeAdded);
		return mapper.map(newPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO post, Integer postID) {

		Post oldPost = postRepo.findById(postID)
				.orElseThrow(() -> new ResourcesNotFoundException("post", "post id", postID));

		oldPost.setAddedDate(new Date());
		oldPost.setContent(post.getContent());
		oldPost.setImagename(post.getImageName());

		Post savedPost = postRepo.save(oldPost);
		return mapper.map(savedPost, PostDTO.class);
	}

	@Override
	public void deletePost(Integer postID) {
		Post post = postRepo.findById(postID)
				.orElseThrow(() -> new ResourcesNotFoundException("Post", "post ID", postID));
		postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
		
		Sort sort = (sortDirection.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);

		List<Post> allPosts = postRepo.findAll(p).getContent();

		List<PostDTO> posts = allPosts.stream().map((post) -> mapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		PostResponse response = new PostResponse();
		response.setContent(posts);
		response.setPageNumber(p.getPageNumber());
		response.setPageSize(p.getPageSize());
		// last page not set and total element is not accurate R&D required
		response.setTotalElements(p.getOffset());
		return response;
	}

	@Override
	public PostDTO getPostById(Integer postID) {

		Post post = postRepo.findById(postID)
				.orElseThrow(() -> new ResourcesNotFoundException("post", "post id", postID));

		return mapper.map(post, PostDTO.class);
	}

	@Override
	public List<PostDTO> getPostsByCategory(Integer categoryID) {
		Category category = categoryRepo.findById(categoryID)
				.orElseThrow(() -> new ResourcesNotFoundException("Category", "Category ID", categoryID));
		List<Post> posts = postRepo.findByCategory(category);
		List<PostDTO> postDTOList = posts.stream().map((post) -> mapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		
		
		return postDTOList;
	}

	@Override
	public List<PostDTO> getPostsByUser(Integer userID) {

		User user = userRepo.findById(userID)
				.orElseThrow(() -> new ResourcesNotFoundException("user", "userID", userID));

		List<Post> postsByUser = postRepo.findByUser(user);

		List<PostDTO> listOfPostsByUser = postsByUser.stream().map((post) -> mapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		return listOfPostsByUser;
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) {
		List<Post> findByTitleContaining = postRepo.findByTitleContaining(keyword);
		List<PostDTO> postDTOs = findByTitleContaining.stream().map((post)->mapper.map(post, PostDTO.class)).collect(Collectors.toList());
		return postDTOs;
	}

}
