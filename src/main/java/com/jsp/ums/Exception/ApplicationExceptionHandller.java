package com.jsp.ums.Exception;

import java.lang.reflect.Field;
import java.util.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandller extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<ObjectError> allErrors = ex.getAllErrors();
		Map<String,String> errors = new HashMap<>();
		
		allErrors.forEach(error->{
			FieldError fielderror = (FieldError) error ;
			errors.put(fielderror.getField(),fielderror.getDefaultMessage());
			
		});
		return structure(HttpStatus.BAD_REQUEST, "invalid data",allErrors);
	}
	
	private ResponseEntity<Object> structure(HttpStatus status, String message, Object rootCause){
		return new ResponseEntity<Object> (Map.of(
				"status",status.value(),
				"message",message,
				"rootCause",rootCause
				),status
				);
	}

	@ExceptionHandler(UserNotFoundByIdException .class)
	public ResponseEntity<Object> handleUserNotFoundById(UserNotFoundByIdException ex) {
		return structure(HttpStatus.NOT_FOUND, ex.getMessage(), "User not Fount With the given Id");
	}
	
	
	public ResponseEntity<Object> handleRuntime(UserNotFoundByIdException ex) {
		return structure(HttpStatus.BAD_REQUEST, ex.getMessage(), "");
	}

}
