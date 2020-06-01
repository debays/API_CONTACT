package com.contactRestAPI.contactAPI.service.company;

import java.util.List;

public class CompanyRequest {
    private int id;
    private int tvaNumber;
    private String companyHeadquarter;
    private List<String> branchs;
    private List<Integer> contactIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTvaNumber() {
        return tvaNumber;
    }

    public void setTvaNumber(int tvaNumber) {
        this.tvaNumber = tvaNumber;
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

    public List<Integer> getContactIds() {
        return contactIds;
    }

    public void setContactIds(List<Integer> contactIds) {
        this.contactIds = contactIds;
    }
}
