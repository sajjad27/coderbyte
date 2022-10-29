package com.coderbyte.auth.model;

import com.coderbyte.shared.exception.CoderbyteException;
import com.coderbyte.shared.exception.model.errorresponse.KeyValue;
import com.coderbyte.shared.service.StringHelper;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
	MALE, FEMALE;

	@JsonCreator
	public static Gender deserialize(String gender) {
		try {
			return Gender.valueOf(gender);
		} catch (IllegalArgumentException e) {
			throw new CoderbyteException("INVALID_GENDER",
					new KeyValue("{VALID_GENDER}", StringHelper.getNamesSeperatedByComma(Gender.class)), "gender");
		}
	}

	public static Gender fromValue(String gender) {
		try {
			return Gender.valueOf(gender);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	@JsonValue
    public String toValue() {
        for (Gender entry : Gender.values()) {
            if (entry == this) {            	
                return entry.name().toLowerCase();
            }
        }

        return null; // or fail
    }

}