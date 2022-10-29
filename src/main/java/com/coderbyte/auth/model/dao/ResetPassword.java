package com.coderbyte.auth.model.dao;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.coderbyte.shared.helper.Regex;


public class ResetPassword extends AuthenticationRequest {

	@NotEmpty(message = "MISSING_NEW_PASSWORD")
	@Pattern(regexp = Regex.REGISTRATION_PASSWORD_REGEX_VALIDATION, message = "INVALID_NEW_PASSWORD")
	private String newPassword;
	
	public ResetPassword() {
		super();
	}

	public ResetPassword(String newPassword) {
		super();
		this.newPassword = newPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
	
}
