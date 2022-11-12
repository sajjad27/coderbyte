package com.coderbyte.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderbyte.comment.model.client.response.ClientCommentResponse;

@Service
public class CommentService {
	
	@Autowired
	private CommentClient commentClient;

	public ClientCommentResponse[] getAll() {
		return this.commentClient.getAllComments();
	}

	public ClientCommentResponse[] getCommentsByPostId(Long postId) {
		return this.commentClient.getCommentsByPostId(postId);
	}

}
