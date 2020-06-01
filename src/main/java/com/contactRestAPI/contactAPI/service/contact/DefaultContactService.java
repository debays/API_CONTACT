package com.contactRestAPI.contactAPI.service.contact;

import com.contactRestAPI.contactAPI.model.Company.Company;
import com.contactRestAPI.contactAPI.model.Company.CompanyRepository;
import com.contactRestAPI.contactAPI.model.Contact.Contact;
import com.contactRestAPI.contactAPI.model.Contact.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class DefaultContactService implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @Transactional
    public ResponseEntity<Void> deleteContact(int id) {
        Contact contact = contactRepository.findById(id);
        // TODO : Débug why message return is not displayed in Postman
        if (contact == null) {
            throw new CustomContactException("Contact not found for id : " + id);
        }
        contactRepository.delete(contact);
        URI location = getUri(contact);
        return ResponseEntity.created(location).build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> createContact(ContactRequest contactRequest) {
        Contact existingContact = contactRepository.findByName(contactRequest.getName());
        if (existingContact != null) {
            throw new CustomContactException("Contact already exist");
        }
        Contact contact = new Contact();
        contactMapping(contactRequest, contact);
        contactRepository.save(contact);
        URI location = getUri(contact);
        return ResponseEntity.created(location).build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> editContact(ContactRequest contactRequest) {
        Contact contactToEdit = contactRepository.findById(contactRequest.getId());
        if (contactToEdit == null) {
            return ResponseEntity.notFound().build();
        }
        contactMapping(contactRequest, contactToEdit);
        contactRepository.save(contactToEdit);
        URI location = getUri(contactToEdit);
        return ResponseEntity.created(location).build();
    }

    private void contactMapping(ContactRequest contactRequest, Contact contact) {
        contact.setAddress(contactRequest.getAddress());
        contact.setName(contactRequest.getName());
        contact.setFirstName(contactRequest.getFirstName());
        if (contactRequest.getTvaNumber() != null) {
            contact.setFreelance(true);
        } else {
            contact.setFreelance(false);
        }
        contact.setTvaNumber(contactRequest.getTvaNumber());
        setCompaniesWithValidation(contactRequest, contact);
    }

    private void setCompaniesWithValidation(ContactRequest contactRequest, Contact contact) {
        List<Company> companyList = new ArrayList<>();
        for (Integer companyId : contactRequest.getCompanyIds()) {
            Company company = companyRepository.findById(companyId.intValue());
            if (company == null) {
                throw new CustomContactException("This company don't exist : " + companyId);
            } else {
                companyList.add(company);
            }
        }
        contact.setCompanies(new HashSet<>(companyList));
    }

    // TODO : contruct custom URI for more usages
    private URI getUri(Contact contact) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contact.getId())
                .toUri();
    }
}
