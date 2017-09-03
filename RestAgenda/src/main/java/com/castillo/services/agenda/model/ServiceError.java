package com.castillo.services.agenda.model;

import java.io.Serializable;

public class ServiceError implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 561672594941447091L;
	
	private String code;
	private String message;
	
	@Deprecated
	public ServiceError() {
		super();
	}

	public ServiceError(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
