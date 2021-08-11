package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    @Autowired
    private final CompanyService companyService;

    public CompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping(path = "/{companyId}")
    public Company getCompany(@PathVariable Integer companyId) {
        return companyService.getById(companyId);
    }

}