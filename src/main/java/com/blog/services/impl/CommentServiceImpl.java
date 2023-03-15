package com.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Comment;
import com.blog.entities.Post;
import com.blog.exceptions.ResourcesNotFoundException;
import com.blog.payloads.CommentDTO;
import com.blog.repositories.CommentRepo;
import com.blog.repositories.PostRepo;
import com.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	ModelMapper mapper;
	
	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postID) {

		Post post = postRepo.findById(postID).orElseThrow(()->new ResourcesNotFoundException("post", "postID", postID));
		
		Comment comment = mapper.map(commentDTO, Comment.class);
		comment.setPost(post);
		Comment saveComment = commentRepo.save(comment);
		
		return mapper.map(saveComment, CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentID) {

		Comment comment = commentRepo.findById(commentID).orElseThrow(()-> new ResourcesNotFoundException("comment", "comment", commentID));
		
		commentRepo.delete(comment);
	}

}
