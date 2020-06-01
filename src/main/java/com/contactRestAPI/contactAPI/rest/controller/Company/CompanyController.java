package com.contactRestAPI.contactAPI.rest.controller.Company;

import com.contactRestAPI.contactAPI.model.Company.Company;
import com.contactRestAPI.contactAPI.model.Company.CompanyRepository;
import com.contactRestAPI.contactAPI.service.company.CompanyRequest;
import com.contactRestAPI.contactAPI.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;
    @Resource
    private CompanyService companyService;

    @PostMapping(value = "/addCompany")
    public ResponseEntity<Void> addCompany(@RequestBody CompanyRequest companyRequest) {
        return companyService.createCompany(companyRequest);
    }

    @PutMapping(value = "/editCompany")
    public ResponseEntity<Void> editContact(@RequestBody CompanyRequest companyRequest) {
        return companyService.editCompany(companyRequest);
    }

    @RequestMapping(value = "/Companies", method = RequestMethod.GET)
    List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

}
