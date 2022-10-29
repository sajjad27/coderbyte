package com.coderbyte.comment.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.coderbyte.post.model.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "comment")
public class Comment {
	
	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "postid", nullable = false, updatable = false)
	private Post post;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String body;
	
	public Comment() {}

	public Comment(Long id, Post post, String name, String email, String body) {
		super();
		this.id = id;
		this.post = post;
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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
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
