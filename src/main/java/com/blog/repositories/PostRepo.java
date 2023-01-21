package com.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category user);
	
	List<Post> findByTitleContaining(String title);
	
	//@Query(select p from Post p where p.title like :key)
	//List<Post> findByTitleContaining(@param("title") String title);
	 
}
