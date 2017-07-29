package com.castillo.services.agenda.dao;

import java.util.List;

import com.castillo.services.agenda.exception.ContactAlreadyExistException;
import com.castillo.services.agenda.exception.ContactNotFoundException;
import com.castillo.services.agenda.model.Contact;

public interface ContactDao {
	void create(Contact contact) throws ContactAlreadyExistException;
	Contact getContact(Long id) throws ContactNotFoundException;
	List<Contact> getContact();
	void delete(Long id) throws ContactNotFoundException;
	void update(Contact contact) throws ContactNotFoundException;
}
