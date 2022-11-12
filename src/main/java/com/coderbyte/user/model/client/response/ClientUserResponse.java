package com.coderbyte.user.model.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientUserResponse {
	
	private long id;
	private String name, gender, email, status;
	
	

}
