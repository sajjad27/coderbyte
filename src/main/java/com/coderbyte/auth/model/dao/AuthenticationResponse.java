package com.coderbyte.auth.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationResponse {

	private String token;
	private String refreshToken;
	
	
}
