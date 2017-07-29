package com.castillo.services.agenda.model;

import java.io.Serializable;
import java.util.List;

public class Contact implements Serializable {

	private static final long serialVersionUID = 3745759776302549494L;
	
	private Long id;
	
	private String name;
	
	private String lastName;
	
	private List<Phone> phones;
	
	@Deprecated
	public Contact() {
		super();
	}

	public Contact(Long id, String name, String lastName, List<Phone> phones) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.phones = phones;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	@Override
	public String toString() {
		return "Contact [name=" + name + ", lastName=" + lastName + ", phones=" + phones + "]";
	}
	
}
