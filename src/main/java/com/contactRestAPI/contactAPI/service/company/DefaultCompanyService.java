package com.contactRestAPI.contactAPI.service.company;


import com.contactRestAPI.contactAPI.model.Company.Company;
import com.contactRestAPI.contactAPI.model.Company.CompanyRepository;
import com.contactRestAPI.contactAPI.model.Contact.Contact;
import com.contactRestAPI.contactAPI.model.Contact.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Set;

@Component
@Transactional
public class DefaultCompanyService implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public ResponseEntity<Void> deleteCompany(int id) {
        Company company = companyRepository.findById(id);
        // TODO : Débug why message return is not displayed in Postman
        if (company == null) {
            throw new CustomCompanyException("Company not found for id : " + id);
        }
        companyRepository.delete(company);
        URI location = getUri(company);
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> createCompany(CompanyRequest companyRequest) {
        Company existingCompany = companyRepository.findByCompanyHeadquarter(companyRequest.getCompanyHeadquarter());
        if (existingCompany != null) {
            throw new CustomCompanyException("Company already exist");
        }
        Company company = new Company();
        companyMapping(companyRequest, company);
        companyRepository.save(company);

        setContactLinks(companyRequest, company);

        URI location = getUri(company);
        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Void> editCompany(CompanyRequest companyRequest) {
        Company companyToEdit = companyRepository.findById(companyRequest.getId());
        if (companyToEdit == null) {
            return ResponseEntity.notFound().build();
        }

        companyToEdit.setTvaNumber(companyRequest.getTvaNumber());
        companyToEdit.setCompanyHeadquarter(companyRequest.getCompanyHeadquarter());
        companyToEdit.setBranchs(companyRequest.getBranchs());

        setContactLinks(companyRequest, companyToEdit);

        URI location = getUri(companyToEdit);
        return ResponseEntity.created(location).build();
    }

    private void setContactLinks(CompanyRequest companyRequest, Company companyToEdit) {
        for (Integer contactId : companyRequest.getContactIds()) {
            Contact contact = contactRepository.findById(contactId.intValue());
            if (contact == null) {
                throw new CustomCompanyException("Contact not found");
            }
            Set<Company> companiesForContact = contact.getCompanies();
            companiesForContact.add(companyToEdit);
            contact.setCompanies(companiesForContact);
            contactRepository.save(contact);
        }
    }

    private void companyMapping(CompanyRequest companyRequest, Company company) {
        company.setTvaNumber(companyRequest.getTvaNumber());
        company.setCompanyHeadquarter(companyRequest.getCompanyHeadquarter());
        company.setBranchs(companyRequest.getBranchs());
    }

    // TODO : Find a way to dé-duplicate
    private Company getNewCompany(Company company) {
        Company companyToCreate = new Company();
        constructCompany(company, companyToCreate);
        return companyToCreate;
    }

    // TODO : Find a way to dé-duplicate
    private Company getEditedCompany(Company company) {
        Company companyToEdit = company;
        constructCompany(company, companyToEdit);
        return companyToEdit;
    }

    // TODO : Construct a mapper
    private void constructCompany(Company company, Company companyToEdit) {
        companyToEdit.setBranchs(company.getBranchs());
        companyToEdit.setTvaNumber(company.getTvaNumber());
        companyToEdit.setContacts(company.getContacts());
        companyToEdit.setCompanyHeadquarter(company.getCompanyHeadquarter());
        companyToEdit.setContacts(company.getContacts());
        companyRepository.save(companyToEdit);
    }

    // TODO : contruct custom URI for more usages
    private URI getUri(Company company) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(company.getId())
                .toUri();
    }
}
