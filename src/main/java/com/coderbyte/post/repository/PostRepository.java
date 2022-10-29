package com.coderbyte.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coderbyte.post.model.dao.response.PostSummaryResponse;
import com.coderbyte.post.model.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
	@Query(value = "SELECT "
			+ " new com.coderbyte.post.model.dao.response.PostSummaryResponse(pst.id, pst.user.id, pst.title, pst.body) "
			+ " FROM Post pst "
			+ " ORDER BY pst.id asc")
	public List<PostSummaryResponse> findAllAsSummary();

	@Query(value = "SELECT "
			+ " new com.coderbyte.post.model.dao.response.PostSummaryResponse(pst.id, pst.user.id, pst.title, pst.body) "
			+ " FROM Post pst "
			+ " WHERE pst.user.id = :userId"
			+ " ORDER BY pst.id asc")
	public List<PostSummaryResponse> findAllAsSummaryByUserId(long userId);
}