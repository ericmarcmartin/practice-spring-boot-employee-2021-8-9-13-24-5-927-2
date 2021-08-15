package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {

    @Autowired
    private final CompanyService companyService;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    public CompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyResponse> getCompanies() {
        return companyMapper.toResponse(companyService.getAllCompanies());
    }

    @GetMapping(path = "/{companyId}")
    public CompanyResponse getCompanyById(@PathVariable Integer companyId) {
        return companyMapper.toResponse(companyService.getById(companyId));
    }

    @GetMapping(path = "/{companyId}/employees")
    public List<EmployeeResponse> getCompanyEmployees(@PathVariable Integer companyId) {
        return employeeMapper.toResponse(companyService.getCompanyEmployeesById(companyId));
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<CompanyResponse> getCompaniesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return companyMapper.toResponse(companyService.getByPageIndexAndPageSize(pageIndex, pageSize));
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CompanyResponse addCompany(@RequestBody CompanyRequest companyRequest) {
        return companyMapper.toResponse(companyService.createCompany(companyMapper.toEntity(companyRequest)));
    }

    @PutMapping(path = "/{id}")
    public CompanyResponse updateCompany(@PathVariable Integer id, @RequestBody Company companyToBeUpdated) {
        return companyMapper.toResponse(companyService.update(id, companyToBeUpdated));
    }

    @DeleteMapping("/{companyId}")
    public void deleteEmployee(@PathVariable Integer companyId) {
        companyService.delete(companyId);
    }
}
