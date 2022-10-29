package com.coderbyte.user.model.dao.response;

import com.coderbyte.auth.model.Gender;
import com.coderbyte.auth.model.UserStatus;

public class UserSummaryResponse {

	private Long id;
	private String name;
	private String email;
	private Gender gender;
	private UserStatus status;
	
	public UserSummaryResponse() {}

	public UserSummaryResponse(Long id, String name, String email, Gender gender, int isActive) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = UserStatus.fromValue(isActive);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}
}
