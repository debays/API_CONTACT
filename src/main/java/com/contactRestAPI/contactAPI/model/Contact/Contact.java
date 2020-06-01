package com.contactRestAPI.contactAPI.model.Contact;

import com.contactRestAPI.contactAPI.model.Company.Company;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private String name;
    private String firstName;
    private Integer tvaNumber;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Company> companies;

    private boolean isFreelance;

    public Contact() {
    }

    public Contact(int id, String address, String name, String firstName, Integer tvaNumber, Set<Company>  companies, boolean isFreelance) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.firstName = firstName;
        this.tvaNumber = tvaNumber;
        this.companies = companies;
        this.isFreelance = isFreelance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getTvaNumber() {
        return tvaNumber;
    }

    public void setTvaNumber(Integer tvaNumber) {
        this.tvaNumber = tvaNumber;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFreelance() {
        return isFreelance;
    }

    public void setFreelance(boolean freelance) {
        isFreelance = freelance;
    }
}
