package com.castillo.services.agenda.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Repository;

import com.castillo.services.agenda.exception.ContactAlreadyExistException;
import com.castillo.services.agenda.exception.ContactNotFoundException;
import com.castillo.services.agenda.model.Contact;
import com.castillo.services.agenda.model.Phone;
import com.castillo.services.agenda.model.PhoneType;

@Repository
public class ContactDaoImpl implements ContactDao {
	//to simulate a name and last name constrain 
	private static Map<String,Contact> contacts = new HashMap<String, Contact>();
	private static Long contactCounter = 0L;
	private static Long phoneCounter = 0L;
	static {
		List<Phone> phones = new ArrayList<Phone>();
		phones.add(new Phone(0L, "(809) 602-2712", PhoneType.Personal));
		Contact dummy  = new Contact(0L, "Dummy", "Value", phones);
		String key = getKey(dummy);
		contacts.put(key, dummy);
	}
	
	public ContactDaoImpl() {
		super();
		
	}
	
	private static String getKey(Contact contact) {
		return contact.getName().toUpperCase() + " " + contact.getLastName().toUpperCase();
	}
	
	public void create(Contact contact) throws ContactAlreadyExistException {
		String key = getKey(contact);
		if(contacts.containsKey(key)) {
			throw new ContactAlreadyExistException(key);
		}
		//INCREASE BEFORE SET THE VALUE
		contact.setId(++contactCounter);
		for(Phone phone : contact.getPhones()) {
			phone.setId(++phoneCounter);
		}
		contacts.put(key, contact);
	}

	public Contact getContact(Long id) throws ContactNotFoundException {
		Contact contact = null;
		for(Contact c : contacts.values()) {
			if (id.equals(c.getId())) {
				contact = c;
				break;
			}
		}
		
		if(contact == null) {
			throw new ContactNotFoundException(id);
		}
		
		
		return contact;
		
	}

	public List<Contact> getContacts() {
		return new ArrayList<Contact>(contacts.values());
	}

	public void delete(Long id) throws ContactNotFoundException {
		String key = null;
		for(Entry<String, Contact> entry : contacts.entrySet()) {
			if(entry.getValue().getId().equals(id)) {
				key = entry.getKey();
				break;
			}
		}
		
		if(key == null) {
			throw new ContactNotFoundException(id);
		}else {
			contacts.remove(key);
		}
		
		
	}

	public void update(Contact contact) throws ContactNotFoundException {
		boolean found = false;
		for(Entry<String, Contact> entry : contacts.entrySet()) {
			if(entry.getValue().getId().equals(contact.getId())) {
				//entry
				contacts.entrySet().remove(entry);
				contacts.put(getKey(contact), contact);
				found = true;
				break;
			}
		}
		
		if(!found) {
			throw new ContactNotFoundException(contact.getId());
		}
	}

}
