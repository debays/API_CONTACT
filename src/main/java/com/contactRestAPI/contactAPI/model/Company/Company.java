package com.contactRestAPI.contactAPI.model.Company;

import com.contactRestAPI.contactAPI.model.Contact.Contact;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String companyHeadquarter;
    @ElementCollection
    private List<String> branchs;
    private int tvaNumber;

    @ManyToMany(mappedBy = "companies",fetch = FetchType.LAZY)
    private Set<Contact> contacts;

    public Company() {
    }

    public Company(String companyHeadquarter, List<String> branchs, int tvaNumber, Set<Contact> contacts) {
        this.companyHeadquarter = companyHeadquarter;
        this.branchs = branchs;
        this.tvaNumber = tvaNumber;
        this.contacts = contacts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyHeadquarter() {
        return companyHeadquarter;
    }

    public void setCompanyHeadquarter(String companyHeadquarter) {
        this.companyHeadquarter = companyHeadquarter;
    }

    public List<String> getBranchs() {
        return branchs;
    }

    public void setBranchs(List<String> branchs) {
        this.branchs = branchs;
    }

    public int getTvaNumber() {
        return tvaNumber;
    }

    public void setTvaNumber(int tvaNumber) {
        this.tvaNumber = tvaNumber;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }
}
