package com.coderbyte.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coderbyte.comment.model.dao.response.CommentSummaryResponse;
import com.coderbyte.comment.model.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	@Query(value = "SELECT "
			+ " new com.coderbyte.comment.model.dao.response.CommentSummaryResponse(cmnt.id, cmnt.post.id, cmnt.name, cmnt.email, cmnt.body) "
			+ " FROM Comment cmnt "
			+ " ORDER BY cmnt.id asc")
	public List<CommentSummaryResponse> findAllAsSummary();
	
	
	@Query(value = "SELECT "
			+ " new com.coderbyte.comment.model.dao.response.CommentSummaryResponse(cmnt.id, cmnt.post.id, cmnt.name, cmnt.email, cmnt.body) "
			+ " FROM Comment cmnt "
			+ " WHERE cmnt.post.id = :postId"
			+ " ORDER BY cmnt.id asc")
	public List<CommentSummaryResponse> findAllAsSummaryByPostIdId(long postId);
}