package com.coderbyte.user.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderbyte.shared.exception.CoderbyteException;
import com.coderbyte.shared.helper.Regex;
import com.coderbyte.user.repository.UserRepository;


@Service
public class UserValidator {

	@Autowired
	private UserRepository userRepository;
	
	public void validateUserId(String workerId) {
		if (!Regex.isPositiveNumber(workerId)) {
			throw new CoderbyteException("NO_USER_ID_FOUND", "header");
		}
		try {
			this.userExistsByUserId(Long.parseLong(workerId));
		} catch (CoderbyteException e) {
			e.getErrors().get(0).setField("header");
			throw e;
		}
	}
	
	public void userExistsByUserId(Long workerId) {
		if (!this.userRepository.existsById(workerId)) {
			throw new CoderbyteException("NO_USER_ID_FOUND");
		}
	}
}
