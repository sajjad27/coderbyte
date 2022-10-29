package com.coderbyte.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coderbyte.comment.service.CommentService;

@RestController
@CrossOrigin
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService postService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll() throws Exception {
		return ResponseEntity.ok(this.postService.getAll());
	}
}
