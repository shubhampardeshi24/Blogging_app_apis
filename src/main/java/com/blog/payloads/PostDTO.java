package com.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blog.entities.Comment;

import lombok.Data;

@Data
public class PostDTO {
	
	private Integer postID;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryDTO category;
	private UserDTO user;
	
	private Set<CommentDTO> comments = new HashSet<>();
}
