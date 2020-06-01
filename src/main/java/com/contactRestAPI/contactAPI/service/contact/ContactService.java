package com.contactRestAPI.contactAPI.service.contact;

import com.contactRestAPI.contactAPI.model.Contact.Contact;
import org.springframework.http.ResponseEntity;

public interface ContactService {
    ResponseEntity<Void> createContact(ContactRequest contactRequest);

    ResponseEntity<Void> editContact(ContactRequest contactRequest);

    ResponseEntity<Void> deleteContact(int id);
}
