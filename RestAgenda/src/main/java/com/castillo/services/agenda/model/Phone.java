package com.castillo.services.agenda.model;

import java.io.Serializable;

public class Phone implements Serializable {

	private static final long serialVersionUID = -3306721362846328577L;
	
	private Long id;
	
	private String number;
	
	private PhoneType type;
	
	@Deprecated
	public Phone() {
		super();
	}

	public Phone(Long id, String number, PhoneType type) {
		super();
		this.id = id;
		this.number = number;
		this.type = type;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public PhoneType getType() {
		return type;
	}

	public void setType(PhoneType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Phone [number=" + number + ", type=" + type + "]";
	}
	
}
