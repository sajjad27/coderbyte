package com.coderbyte.shared.model;

import com.coderbyte.shared.exception.CoderbyteException;
import com.coderbyte.shared.exception.model.errorresponse.KeyValue;
import com.coderbyte.shared.service.StringHelper;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum RequestType {
	NEW, UPDATE, DELETE;

	@JsonCreator
	public static RequestType deserialize(String occupation) {
		try {
			return RequestType.valueOf(occupation.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new CoderbyteException("INVALID_OCCUPATION",
					new KeyValue("{VALID_OCCUPATIONS}", StringHelper.getNamesSeperatedByComma(RequestType.class)), "occupation");
		}
	}

	public static RequestType fromValue(String occupation) {
		try {
			return RequestType.valueOf(occupation);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

}