package com.castillo.services.agenda.exception;

public class ContactNotFoundException extends ServiceException {

	
	private static final long serialVersionUID = 7041738642403092561L;

	public ContactNotFoundException(long id) {
		super(String.format("A Contact with id: %s doesn't exist", id));
	}
}
