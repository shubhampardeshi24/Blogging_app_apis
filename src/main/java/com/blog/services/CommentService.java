package com.blog.services;

import com.blog.payloads.CommentDTO;

public interface CommentService {
	
	
	CommentDTO createComment(CommentDTO commentDTO, Integer postID);
	
	void deleteComment(Integer commentID);
}
