package com.castillo.services.agenda.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.castillo.services.agenda.exception.ContactAlreadyExistException;
import com.castillo.services.agenda.exception.ContactNotFoundException;
import com.castillo.services.agenda.model.Contact;
import com.castillo.services.agenda.service.ContactService;
import com.castillo.services.agenda.validator.ContactRequestValidator;

@RestController
public class AgendaRestController {
	
	@Autowired
	private ContactService contactService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	    binder.setValidator(new ContactRequestValidator());
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String hello() {
		return "The service is up...";
	}
	
	@RequestMapping(path = "/contact/", method = RequestMethod.GET)
	public ResponseEntity<List<Contact>> getContacts(){
		ResponseEntity<List<Contact>> response = null;
		List<Contact> contacts = this.contactService.getContact();
		if(contacts == null || contacts.isEmpty()) {
			response = new ResponseEntity<List<Contact>>(HttpStatus.NO_CONTENT);
		}else {
			response = new ResponseEntity<List<Contact>>(contacts, HttpStatus.OK);
		}
		
		return response;
	}
	
	@RequestMapping(path = "/contact/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contact> getContact(@PathVariable("id") Long id){
		ResponseEntity<Contact> response = null;
		try {
			Contact contact = this.contactService.getContact(id);
			response = new ResponseEntity<Contact>(contact, HttpStatus.OK);
		} catch (ContactNotFoundException e) {
			response = new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
			e.printStackTrace();
		}
		return response;
	}
	
	@RequestMapping(path = "/contact/", method = RequestMethod.POST)
	public ResponseEntity<Void> createContact(@Valid @RequestBody Contact contact, UriComponentsBuilder ucBuilder){
		ResponseEntity<Void> response = null;
		try {
			this.contactService.create(contact);
			HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/contact/{id}").buildAndExpand(contact.getId()).toUri());
			response = new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (ContactAlreadyExistException e) {
			e.printStackTrace();
			response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		return response;
	}
	
	@RequestMapping(path = "/contact/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Contact> updateContact(@PathVariable("id") Long id, @RequestBody Contact contact){
		ResponseEntity<Contact> response = null;
		try {
			this.contactService.update(contact);
			response = new ResponseEntity<Contact>(contact,HttpStatus.OK);
		} catch (ContactNotFoundException e) {
			e.printStackTrace();
			response = new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@RequestMapping(path = "/contact/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Contact> deleteContact(@PathVariable("id") Long id){
		ResponseEntity<Contact> response = null;
		
		try {
			this.contactService.delete(id);
			response = new ResponseEntity<Contact>(HttpStatus.NO_CONTENT);
		} catch (ContactNotFoundException e) {
			e.printStackTrace();
			response = new ResponseEntity<Contact>(HttpStatus.NOT_FOUND);
		}
		
		return response;
	}
	
}
