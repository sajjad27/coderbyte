package com.coderbyte.comment.model.dao.response;

public class CommentSummaryResponse {

	private Long id;
	private Long postId;
	private String name;
	private String email;
	private String body;
	
	public CommentSummaryResponse() {}

	public CommentSummaryResponse(Long id, Long postId, String name, String email, String body) {
		super();
		this.id = id;
		this.postId = postId;
		this.name = name;
		this.email = email;
		this.body = body;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	
}
