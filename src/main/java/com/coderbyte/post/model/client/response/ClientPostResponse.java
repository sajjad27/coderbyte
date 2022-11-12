package com.coderbyte.post.model.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientPostResponse {
	
	private long id;
	private String title, body;
	
	@JsonProperty("user_id")
	private long userId;
	
	

}
