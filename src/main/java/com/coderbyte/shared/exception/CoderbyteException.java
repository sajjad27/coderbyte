package com.coderbyte.shared.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.coderbyte.shared.exception.model.errorresponse.ErrorWrapper;
import com.coderbyte.shared.exception.model.errorresponse.KeyValue;

public class CoderbyteException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	// create and initialize with one element 
	private List<ErrorWrapper> errors = new ArrayList<ErrorWrapper>(new ArrayList<ErrorWrapper>(Arrays.asList(new ErrorWrapper())));

	public CoderbyteException() {}
	
	public CoderbyteException(List<ErrorWrapper> errors) {
		this.errors = errors;
	}
	
	public CoderbyteException(String errorCode) {
		this.errors.set(0, new ErrorWrapper(errorCode));
	}

	public CoderbyteException(String errorCode, String field) {
		ErrorWrapper errorWapper = new ErrorWrapper(errorCode, field);
		this.errors.set(0, errorWapper);
	}
	
	public CoderbyteException(String errorCode, List<KeyValue> keysValues, String field) {
		this.errors.set(0, new ErrorWrapper(errorCode, keysValues, field));

	}

	public CoderbyteException(String errorCode, KeyValue keysValues, String field) {
		this.errors.set(0, new ErrorWrapper(errorCode, new ArrayList<KeyValue>(Arrays.asList(keysValues)), field));
	}
	
	

	public List<ErrorWrapper> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorWrapper> errors) {
		this.errors = errors;
	}
		
}
