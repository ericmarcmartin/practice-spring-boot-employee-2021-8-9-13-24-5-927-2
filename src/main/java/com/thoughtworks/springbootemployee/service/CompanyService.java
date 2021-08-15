package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.CompanyDoesNotExistException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class CompanyService {

    @Autowired
    private final CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getById(Integer companyId) {
        return companyRepository
                .findById(companyId)
                .orElseThrow(() -> new CompanyDoesNotExistException(companyId));
    }

    public List<Employee> getCompanyEmployeesById(Integer companyId) {
        List<Employee> list = new ArrayList<>();
        for (Employee employee : employeeRepository.findAll()) {
            if (nonNull(employee.getCompanyId()) && employee.getCompanyId().equals(companyId)) {
                list.add(employee);
            }
        }
        return list;
    }

    public List<Company> getByPageIndexAndPageSize(Integer pageIndex, int pageSize) {
        return companyRepository.findAll(PageRequest.of(pageIndex - 1, pageSize)).toList();

    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company update(int companyId, Company companyToBeUpdated) {
        return companyRepository.save(updateCompanyInfo(getById(companyId), companyToBeUpdated));
    }

    private Company updateCompanyInfo(Company company, Company companyToBeUpdated) {
        if (companyToBeUpdated.getCompanyName() != null) {
            company.setCompanyName(companyToBeUpdated.getCompanyName());
        }
        return company;
    }

    public void delete(Integer companyId) {
        companyRepository
                .delete(companyRepository.findById(companyId)
                        .orElseThrow(() -> new CompanyDoesNotExistException(companyId)));
    }
}
