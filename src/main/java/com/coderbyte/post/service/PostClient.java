package com.coderbyte.post.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.coderbyte.post.model.client.response.ClientPostResponse;
import com.coderbyte.shared.client.GorestClient;

@Service
public class PostClient {
	
	@Autowired
	private GorestClient gorestClient;

	private String postsUrl;
	
	@Value("${client.posts-url}")
	public void setPostsUrl(String postsUrl) {
		this.postsUrl = postsUrl;
	}
	
	public ClientPostResponse[] getAllPosts() {
		return this.gorestClient.get(this.postsUrl, ClientPostResponse[].class, "posts");
	}

	public ClientPostResponse[] getPostsByUserId(long userId) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("user_id", String.valueOf(userId));
		return this.gorestClient.get(this.postsUrl, ClientPostResponse[].class, "posts", params);
	}
}
