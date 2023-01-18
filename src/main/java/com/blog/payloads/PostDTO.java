package com.blog.payloads;

import java.util.Date;

import com.blog.entities.Category;
import com.blog.entities.User;

import lombok.Data;

@Data
public class PostDTO {
	
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryDTO category;
	private UserDTO user;

}
