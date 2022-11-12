package com.coderbyte.post.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coderbyte.post.service.PostService;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getPosts(@RequestParam("user_id") Optional<Long> userId) throws Exception {
		if (userId.isPresent()) {
			return ResponseEntity.ok(this.postService.getPostsByUserId(userId.get()));
		} else {
			return ResponseEntity.ok(this.postService.getAll());
		}
	}
}
