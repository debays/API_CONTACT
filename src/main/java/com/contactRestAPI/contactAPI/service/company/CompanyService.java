package com.contactRestAPI.contactAPI.service.company;

import com.contactRestAPI.contactAPI.model.Company.Company;
import org.springframework.http.ResponseEntity;

public interface CompanyService {
    ResponseEntity<Void> createCompany(CompanyRequest companyRequest);

    ResponseEntity<Void> editCompany(CompanyRequest companyRequest);

    ResponseEntity<Void> deleteCompany(int id);
}
