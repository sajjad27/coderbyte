package com.coderbyte.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderbyte.comment.model.dao.response.CommentSummaryResponse;
import com.coderbyte.comment.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	public List<CommentSummaryResponse> getAll() {
		return this.commentRepository.findAllAsSummary();
	}

	public List<CommentSummaryResponse> findCommentsByPostId(long parseLong) {
		return this.commentRepository.findAllAsSummaryByPostIdId(parseLong);
	}

}
