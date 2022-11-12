package com.coderbyte.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.coderbyte.comment.model.client.response.ClientCommentResponse;
import com.coderbyte.shared.client.GorestClient;

@Service
public class CommentClient {
	
	@Autowired
	private GorestClient gorestClient;

	private String commentUrl;
	
	@Value("${client.comments-url}")
	public void setCommentsUrl(String commentUrl) {
		this.commentUrl = commentUrl;
	}
	
	public ClientCommentResponse[] getAllComments() {
		return this.gorestClient.get(this.commentUrl, ClientCommentResponse[].class, "comments");
	}

	public ClientCommentResponse[] getCommentsByPostId(long postId) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("post_id", String.valueOf(postId));
		return this.gorestClient.get(this.commentUrl, ClientCommentResponse[].class, "comments", params);
	}
}
