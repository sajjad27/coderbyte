package com.coderbyte.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coderbyte.post.service.PostService;

@RestController
@CrossOrigin
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostService postService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll() throws Exception {
		return ResponseEntity.ok(this.postService.getAll());
	}
	
	@RequestMapping(value = "/{postId}/comments", method = RequestMethod.GET)
	public ResponseEntity<?> getCommentsForSinglePost(@PathVariable("postId") String postId) throws Exception {
		return ResponseEntity.ok(this.postService.getCommentsForSinglePost(postId));
	}
}
