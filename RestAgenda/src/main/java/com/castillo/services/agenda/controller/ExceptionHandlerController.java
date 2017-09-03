package com.castillo.services.agenda.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.castillo.services.agenda.exception.ServiceException;
import com.castillo.services.agenda.model.ServiceError;

@EnableWebMvc
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
	private static final Logger log = LogManager.getLogger();
	
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ServiceError> handleServiceException(ServiceException se){
		return processException(se);
	}

	private ResponseEntity<ServiceError> processException(Exception se) {
		String[] codeMessage = getCodeMessage(se);
		ServiceError error = new ServiceError(codeMessage[0],codeMessage[1]);	
		return new ResponseEntity<ServiceError>(error,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({Exception.class,NullPointerException.class})
	private ResponseEntity<ServiceError> handleException(Exception e){
		log.error("Unhandled exception", e);
		ServiceError error = new ServiceError("500","Internal Error");	
		return new ResponseEntity<ServiceError>(error,HttpStatus.BAD_REQUEST);
	}
	
	private String[] getCodeMessage(Exception e) {
		return new String[] {"ERROR-CODE", e.getMessage()};
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}
	
}
