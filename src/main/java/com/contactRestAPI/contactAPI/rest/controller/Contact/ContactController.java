package com.contactRestAPI.contactAPI.rest.controller.Contact;

import com.contactRestAPI.contactAPI.model.Contact.Contact;
import com.contactRestAPI.contactAPI.model.Contact.ContactRepository;
import com.contactRestAPI.contactAPI.service.contact.ContactRequest;
import com.contactRestAPI.contactAPI.service.contact.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;
    @Resource
    private ContactService contactService;

    @PostMapping(value = "/addContact")
    public ResponseEntity<Void> addContact(@RequestBody ContactRequest contactRequest) {
        return contactService.createContact(contactRequest);
    }

    @PutMapping(value = "/editContact")
    public ResponseEntity<Void> editContact(@RequestBody ContactRequest contactRequest) {
        return contactService.editContact(contactRequest);
    }

    @DeleteMapping(value = "/deleteContact")
    public ResponseEntity<Void> deleteContact(@RequestBody ContactRequest contactRequest) {
        return contactService.deleteContact(contactRequest.getId());
    }

    @RequestMapping(value = "/Contacts", method = RequestMethod.GET)
    List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
}
