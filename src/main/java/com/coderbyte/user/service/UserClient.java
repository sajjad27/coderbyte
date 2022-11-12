package com.coderbyte.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.coderbyte.shared.client.GorestClient;
import com.coderbyte.user.model.client.response.ClientUserResponse;

@Service
public class UserClient {
	
	@Autowired
	private GorestClient gorestClient;

	private String usersUrl;
	
	@Value("${client.users-url}")
	public void setUsersUrl(String usersUrl) {
		this.usersUrl = usersUrl;
	}
	
	public ClientUserResponse[] getAllUsers() {
		return this.gorestClient.get(this.usersUrl, ClientUserResponse[].class, "users");
	}
}
