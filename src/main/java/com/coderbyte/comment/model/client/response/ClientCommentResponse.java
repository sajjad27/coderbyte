package com.coderbyte.comment.model.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientCommentResponse {
	
	private long id;
	private String name, email, body;
	
	@JsonProperty("post_id")
	private long postId;
	
	

}
