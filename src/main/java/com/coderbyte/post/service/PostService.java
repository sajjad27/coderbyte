package com.coderbyte.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderbyte.post.model.client.response.ClientPostResponse;

@Service
public class PostService {
	
	@Autowired
	private PostClient postClient;

	public ClientPostResponse[] getAll() {
		return this.postClient.getAllPosts();
	}

	public ClientPostResponse[] getPostsByUserId(long userId) {
		return this.postClient.getPostsByUserId(userId);
	}
}
