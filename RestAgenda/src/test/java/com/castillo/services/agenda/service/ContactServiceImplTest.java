package com.castillo.services.agenda.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import com.castillo.services.agenda.configuration.AgendaConfiguration;
import com.castillo.services.agenda.exception.ContactAlreadyExistException;
import com.castillo.services.agenda.exception.ContactNotFoundException;
import com.castillo.services.agenda.model.Contact;
import com.castillo.services.agenda.model.Phone;
import com.castillo.services.agenda.model.PhoneType;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AgendaConfiguration.class})
public class ContactServiceImplTest {
	
	@Autowired
	private ContactService contactService;
	
	@Test
	public void testCreate() throws ContactAlreadyExistException {
		final Long dummyId = 0L;
		int csize = contactService.getContacts().size();
		
		
		List<Phone> phones = new ArrayList<Phone>();
		phones.add(new Phone(dummyId, "(809) 123-4567", PhoneType.Home));
		Contact c = new Contact(dummyId, "jose", "Martines", phones);
		this.contactService.create(c);
		Assert.isTrue((contactService.getContacts().size() > csize), "collection don't have a new object");
		assertNotNull("contact wasn't created",contactService.getContacts().get(csize));
		
		
	}

	@Test(expected = ContactAlreadyExistException.class)
	public void testCreate_shouldTrowContactAlreadyExistException() throws ContactAlreadyExistException {
		List<Phone> phones = new ArrayList<Phone>();
		Long dummyId = 0L;
		phones.add(new Phone(dummyId , "(809) 123-4567", PhoneType.Home));
		Contact c = new Contact(dummyId, "joan", "Castillo", phones);
		this.contactService.create(c);
		this.contactService.create(c);
	}
	
	@Test
	public void testGetContactLong() throws ContactNotFoundException {
		assertNotNull("Contract 0 is null", contactService.getContact(0L));
	}
	
	@Test(expected = ContactNotFoundException.class)
	public void testGetContractLog_shouldTrowContactNotFoundException() throws ContactNotFoundException {
		contactService.getContact(100L);
	}

	@Test
	public void testGetContacts() {
		assertNotNull("Error getting list", contactService.getContacts());
	}

	@Test
	public void testDelete() throws ContactAlreadyExistException, ContactNotFoundException {
		List<Phone> phones = new ArrayList<Phone>();
		Long dummyId = 0L;
		phones.add(new Phone(dummyId , "(809) 123-4567", PhoneType.Home));
		Contact c = new Contact(dummyId, "TO", "Delete", phones);
		this.contactService.create(c);
		int size = contactService.getContacts().size();
		contactService.delete(c.getId());
		Assert.isTrue(size > contactService.getContacts().size(), "Didn't delete anything");
		//fail("Not yet implemented");
	}
	
	@Test(expected= ContactNotFoundException.class)
	public void testDelete_ContactNotFoundException() throws ContactNotFoundException {
		this.contactService.delete(100L);
	}
	
	@Test
	public void testUpdate() throws ContactAlreadyExistException, ContactNotFoundException {
		List<Phone> phones = new ArrayList<Phone>();
		Long dummyId = 0L;
		phones.add(new Phone(dummyId , "(809) 123-4567", PhoneType.Home));
		Contact c = new Contact(dummyId, "TO", "Update", phones);
		this.contactService.create(c);
		
		Contact cnew = new Contact(c.getId(), "pedro","jose", phones);
		this.contactService.update(cnew);
		
		c = this.contactService.getContact(cnew.getId());
		assertEquals(cnew.getName(), c.getName());
	}
	
	@Test(expected=ContactNotFoundException.class)
	public void testUpdate_shouldThrowContactNotFoundException() throws ContactNotFoundException {
		Contact dummyContact = new Contact(100L,"dummy","dummy", new ArrayList<Phone>());
		this.contactService.update(dummyContact);
		
		
	}
	
}
