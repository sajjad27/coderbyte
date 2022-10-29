package com.coderbyte.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coderbyte.user.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll() throws Exception {
		return ResponseEntity.ok(this.userService.getAll());
	}
	
	@RequestMapping(value = "/{userId}/posts", method = RequestMethod.GET)
	public ResponseEntity<?> getPostsForSingleUser(@PathVariable("userId") String userId) throws Exception {
		return ResponseEntity.ok(this.userService.getPostsForSingleUser(userId));
	}
}
