package com.castillo.services.agenda.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.castillo.services.agenda.model.Contact;

public class ContactRequestValidator implements Validator {

	public boolean supports(Class<?> classes) {
		return Contact.class.equals(classes);
	}

	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
		ValidationUtils.rejectIfEmpty(errors, "lastName", "lastName.empty");
		ValidationUtils.rejectIfEmpty(errors, "phones", "phones.empty");
		ValidationUtils.rejectIfEmpty(errors, "phones", "phones.empty");
	}

}
