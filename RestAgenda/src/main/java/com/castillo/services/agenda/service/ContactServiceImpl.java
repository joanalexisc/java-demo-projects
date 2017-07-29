package com.castillo.services.agenda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.castillo.services.agenda.dao.ContactDao;
import com.castillo.services.agenda.exception.ContactAlreadyExistException;
import com.castillo.services.agenda.exception.ContactNotFoundException;
import com.castillo.services.agenda.model.Contact;

@Service("contactService")
@Transactional
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	ContactDao contactDao;
	
	public void create(Contact contact) throws ContactAlreadyExistException {
		contactDao.create(contact);		
	}

	public Contact getContact(Long id) throws ContactNotFoundException {
		// TODO Auto-generated method stub
		return this.contactDao.getContact(id);
	}

	public List<Contact> getContact() {
		// TODO Auto-generated method stub
		return this.contactDao.getContact();
	}

	public void delete(Long id) throws ContactNotFoundException {
		this.contactDao.delete(id);
	}

	public void update(Contact contact) throws ContactNotFoundException {
		this.contactDao.update(contact);
		
	}

}
