package com.coderbyte.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderbyte.comment.model.dao.response.CommentSummaryResponse;
import com.coderbyte.comment.service.CommentService;
import com.coderbyte.post.model.dao.response.PostSummaryResponse;
import com.coderbyte.post.repository.PostRepository;
import com.coderbyte.post.validate.PostValidator;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PostValidator postValidator;
	
	@Autowired
	private CommentService commentService;

	public List<PostSummaryResponse> getAll() {
		return this.postRepository.findAllAsSummary();
	}

	public List<PostSummaryResponse> getPostsByUserId(long userId) {
		return this.postRepository.findAllAsSummaryByUserId(userId);
	}

	public List<CommentSummaryResponse> getCommentsForSinglePost(String postId) {
		this.postValidator.validatePostId(postId);
		return this.commentService.findCommentsByPostId(Long.parseLong(postId));
	}

}
