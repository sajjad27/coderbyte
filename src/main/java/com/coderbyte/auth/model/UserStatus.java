package com.coderbyte.auth.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UserStatus {
	ACTIVE(1), INACTIVE(0);

	private int value;

	private UserStatus(int value) {
		this.value = value;
	}

	public static UserStatus fromValue(int status) {
		try {
			for (UserStatus userStatus : UserStatus.values()) {
				if (userStatus.value == status) {
					return userStatus;
				}
			}
		} catch (IllegalArgumentException e) {
			return null;
		}
		return null;
	}

	@JsonValue
	public String toValue() {
		for (UserStatus entry : UserStatus.values()) {
			if (entry == this)
				return entry.name().toLowerCase();
		}

		return null;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}