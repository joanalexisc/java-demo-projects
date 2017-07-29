package com.castillo.services.agenda.exception;

public class ContactAlreadyExistException extends Exception {

	private static final long serialVersionUID = -3661715038693155686L;

	public ContactAlreadyExistException(String userName) {
		super(String.format("A Contact with name %s already exist", userName));
	}
}
