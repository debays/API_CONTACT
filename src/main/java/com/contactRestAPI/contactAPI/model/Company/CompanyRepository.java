package com.contactRestAPI.contactAPI.model.Company;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findById(int id);
    Company findByCompanyHeadquarter(String name);
}
