package com.coderbyte.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderbyte.user.model.client.response.ClientUserResponse;

@Service
public class UserService {

	@Autowired
	private UserClient userClient;

	public ClientUserResponse[] getAll() {
		return this.userClient.getAllUsers();
	}

}
