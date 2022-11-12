package com.coderbyte.post.validate;

import org.springframework.stereotype.Service;

import com.coderbyte.shared.exception.CoderbyteException;
import com.coderbyte.shared.helper.Regex;

@Service
public class PostValidator {
	
	public void validatePostId(String workerId) {
		if (!Regex.isPositiveNumber(workerId)) {
			throw new CoderbyteException("NO_POST_ID_FOUND", "header");
		}
		try {
			this.postExistsByPostId(Long.parseLong(workerId));
		} catch (CoderbyteException e) {
			e.getErrors().get(0).setField("header");
			throw e;
		}
	}
	
	public void postExistsByPostId(Long workerId) {
//		if (!this.postRepository.existsById(workerId)) {
//			throw new CoderbyteException("NO_POST_ID_FOUND");
//		}
	}
}
