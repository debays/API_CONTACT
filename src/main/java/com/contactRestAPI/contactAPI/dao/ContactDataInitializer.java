package com.contactRestAPI.contactAPI.dao;

import com.contactRestAPI.contactAPI.model.Company.Company;
import com.contactRestAPI.contactAPI.model.Company.CompanyRepository;
import com.contactRestAPI.contactAPI.model.Contact.Contact;
import com.contactRestAPI.contactAPI.model.Contact.ContactRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ContactDataInitializer implements InitializingBean {
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    CompanyRepository companyRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        Company company001 = new Company();
        company001.setCompanyHeadquarter("Headquarter 001");
        company001.setTvaNumber(001);
        List<String> branchs001 = new ArrayList<>();
        branchs001.add("Branch 001");
        branchs001.add("Branch 002");
        company001.setBranchs(branchs001);
        companyRepository.save(company001);

        Company company002 = new Company();
        company002.setCompanyHeadquarter("Headquarter 002");
        company002.setTvaNumber(002);
        List<String> branchs002 = new ArrayList<>();
        branchs002.add("Branch 001");
        branchs002.add("Branch 002");
        company002.setBranchs(branchs001);
        companyRepository.save(company002);

        Contact contact001 = new Contact();
        contact001.setAddress("Addresse 001");
        contact001.setName("Name 001");
        contact001.setFirstName("First name 001");
        contact001.setTvaNumber(123);
        contact001.setFreelance(true);
        Set<Company> companiesForContact001 = new HashSet<>();
        companiesForContact001.add(company001);
        contact001.setCompanies(companiesForContact001);
        contactRepository.save(contact001);

        Contact contact002 = new Contact();
        contact002.setAddress("Addresse 002");
        contact002.setName("Name 002");
        contact002.setFirstName("First name 002");
        contact002.setTvaNumber(null);
        contact002.setFreelance(false);
        Set<Company> companiesForContact002 = new HashSet<>();
        companiesForContact002.add(company001);
        companiesForContact002.add(company002);
        contact001.setCompanies(companiesForContact002);
        contactRepository.save(contact002);
    }


}
