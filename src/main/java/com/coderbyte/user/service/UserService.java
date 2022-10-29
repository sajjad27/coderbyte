package com.coderbyte.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderbyte.post.model.dao.response.PostSummaryResponse;
import com.coderbyte.post.service.PostService;
import com.coderbyte.user.model.dao.response.UserSummaryResponse;
import com.coderbyte.user.repository.UserRepository;
import com.coderbyte.user.validate.UserValidator;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserValidator userValidator ;
	
	@Autowired
	private PostService postService;

	public List<UserSummaryResponse> getAll() {
		return this.userRepository.findAllAsSummary();
	}

	public List<PostSummaryResponse> getPostsForSingleUser(String userId) {
		this.userValidator.validateUserId(userId);
		return this.postService.getPostsByUserId(Long.parseLong(userId));
	}

}
