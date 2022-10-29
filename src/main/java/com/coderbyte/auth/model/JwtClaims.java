package com.coderbyte.auth.model;

public class JwtClaims {

	
	// if you want to add more, you first need to add the parameter here, load it in the login , 
	// map it if you want to generate token, reuse it if you want to convert from token to jwtClaims
	private Long userId;
	
	public JwtClaims() {}
	

	public JwtClaims(Long userId) {
		super();
		this.userId = userId;
	}


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
