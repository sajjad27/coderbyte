package com.coderbyte.comment.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coderbyte.comment.service.CommentService;

@RestController
@CrossOrigin
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getComments(@RequestParam("post_id") Optional<Long> post) throws Exception {
		if (post.isPresent()) {
			return ResponseEntity.ok(this.commentService.getCommentsByPostId(post.get()));
		} else {
			return ResponseEntity.ok(this.commentService.getAll());
		}
	}
}
