package com.contactRestAPI.contactAPI.service.contact;

import java.util.List;

public class ContactRequest {
    private int id;
    private String address;
    private String name;
    private String firstName;
    private Integer tvaNumber;
    private List<Integer> companyIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Integer> getCompanyIds() {
        return companyIds;
    }

    public void setCompanyIds(List<Integer> companyIds) {
        this.companyIds = companyIds;
    }

}
