package com.castillo.services.agenda.service;

import java.util.List;

import com.castillo.services.agenda.exception.ContactAlreadyExistException;
import com.castillo.services.agenda.exception.ContactNotFoundException;
import com.castillo.services.agenda.model.Contact;

public interface ContactService {
	void create(Contact contact) throws ContactAlreadyExistException;
	Contact getContact(Long id)throws ContactNotFoundException;
	List<Contact> getContacts();
	void delete(Long id) throws ContactNotFoundException;
	void update(Contact contact) throws ContactNotFoundException;
}
