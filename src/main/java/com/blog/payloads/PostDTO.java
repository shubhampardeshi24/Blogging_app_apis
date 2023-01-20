package com.blog.payloads;

import java.util.Date;

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
}
