package com.coderbyte.shared.exception;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.coderbyte.shared.exception.model.errorresponse.ErrorResponse;
import com.coderbyte.shared.exception.model.errorresponse.KeyValue;
import com.coderbyte.shared.helper.ErrorResponseMapper;
import com.coderbyte.shared.helper.RegexHelper;
import com.coderbyte.shared.service.StringHelper;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class ControllerAdvisor {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleException(MethodArgumentNotValidException ex) {
		List<ObjectError> errors = ex.getBindingResult().getAllErrors();
		ErrorResponse errorResponse = ErrorResponseMapper.map(errors);
		return errorResponse;
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleException(HttpMessageNotReadableException ex) throws Throwable {

		Exception cause = (Exception) ex.getCause();
		ErrorResponse errorResponse = null;
		// enum not correct, thrown in the enum type annotated by @JsonCreator
		CoderbyteException coderbyteException = cause != null ? (CoderbyteException) cause.getCause() : null;

		if (coderbyteException != null) {
			errorResponse = ErrorResponseMapper.map(coderbyteException);
		} else if (ex.getMessage().startsWith("Required request body is missing")) {
			errorResponse = ErrorResponseMapper.map("MISSING_BODY", null);
		} else if (ex.getCause() instanceof InvalidFormatException) {
			errorResponse = this.handleInvalidFormatException((InvalidFormatException) ex.getCause());
		} else {
			errorResponse = ErrorResponseMapper.map("BODY_NOT_READABLE", null);
		}
		return errorResponse;
	}

	@ExceptionHandler(InvalidFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleInvalidFormatException(InvalidFormatException ex) {
		
		String fieldName = "";
		for(Reference reference: ex.getPath()) {
			if(reference.getFieldName() != null) {
				fieldName += "." + reference.getFieldName();
			} else if(reference.getIndex() >= 0) {
				fieldName += "[" + reference.getIndex() + "]";
			}
		}
		ErrorResponse errorResponse = ErrorResponseMapper.mapWithSingleKeyValue("INVALID_FORMAT", new KeyValue("{VALID_DATA_TYPE}", ex.getTargetType().getName()) , fieldName.substring(1));
		return errorResponse;
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleNumberFormatException(MethodArgumentTypeMismatchException ex) {		
		String foundedType = ex.getValue().getClass().getName();
		String expectedType = ex.getParameter().getGenericParameterType().getTypeName();
//		List<String> expectedAndFoundTypes = RegexHelper.getTextBetweenByChar(ex.getMessage(), '\'');
		
		List<KeyValue> keyValues = new ArrayList<KeyValue>();
		keyValues.add(new KeyValue("{{PARAMETER_NAME}}", ex.getName()));
		keyValues.add(new KeyValue("{{FOUND_TYPE}}", foundedType));
		keyValues.add(new KeyValue("{{EXPECTED_TYPE}}", expectedType));
		ErrorResponse errorResponse = ErrorResponseMapper.map("NUMBER_FORMAT_MISMATCH", keyValues , "header");
		return errorResponse;
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ResponseBody
	public ErrorResponse handleException(HttpRequestMethodNotSupportedException ex) {
		String requestedMethod = ex.getMethod();
		String supportedMethods = StringHelper.getStringsSeperatedByComma(ex.getSupportedMethods());
		List<KeyValue> keysValues = new ArrayList<KeyValue>();
		keysValues.add(new KeyValue("{RQ_METHOD}", requestedMethod));
		keysValues.add(new KeyValue("{VALID_METHODS}", supportedMethods));
		ErrorResponse errorResponse = ErrorResponseMapper.map("NOT_ALLOWED_METHOD", keysValues, null);
		return errorResponse;
	}

	@ExceptionHandler(CredentialsExpiredException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ErrorResponse handleCoderbyteException(CredentialsExpiredException e) {
		return ErrorResponseMapper.map("INACTIVE_USER_ERROR");
	}

	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleCoderbyteException(BadCredentialsException e) {
		return ErrorResponseMapper.map("BAD_CREDENTIALS");
	}

	@ExceptionHandler(CoderbyteException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleCoderbyteException(CoderbyteException fe) {
		return ErrorResponseMapper.map(fe);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse missingRequestParams(MissingServletRequestParameterException fe) {

		return ErrorResponseMapper.mapWithSingleKeyValue("MISSING_HEADER_PARAMETERS",
				new KeyValue("{PARAMETER_NAME}", fe.getParameterName()), "header");

	}
	
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public ErrorResponse handleAccessDeniedException(AccessDeniedException ae) {
		return ErrorResponseMapper.map(new CoderbyteException("FORBIDDEN_ACCESS"));
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse handleException(Exception ex) {
		ex.printStackTrace();
		return ErrorResponseMapper.map("INTERNAL_SERVER_ERROR", null);
	}

}