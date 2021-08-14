//package com.thoughtworks.springbootemployee.repository;
//
//import com.thoughtworks.springbootemployee.entity.Company;
//import com.thoughtworks.springbootemployee.entity.Employee;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Repository
//public class RetiringCompanyRepository {
//    private final List<Company> companies = new ArrayList<>();
//
//    public RetiringCompanyRepository() {
//        List<Employee> employees1 = new ArrayList<>();
//        employees1.add(employeesDataFactory().get(0));
//        employees1.add(employeesDataFactory().get(1));
//        employees1.add(employeesDataFactory().get(2));
//
//        List<Employee> employees2 = new ArrayList<>();
//        employees2.add(employeesDataFactory().get(3));
//        employees2.add(employeesDataFactory().get(4));
//        employees2.add(employeesDataFactory().get(5));
//
//        companies.add(new Company(1, "Alibaba", employees1));
//        companies.add(new Company(2, "Shoppee", employees2));
//        companies.add(new Company(3, "Lazada", employees1));
//        companies.add(new Company(4, "Zalora", employees2));
//        companies.add(new Company(5, "Shein", employees1));
//        companies.add(new Company(6, "Amazon", employees2));
//    }
//
//    private List<Employee> employeesDataFactory() {
//        List<Employee> employees = new ArrayList<>();
//        employees.add(new Employee(1, "Francis", 24, "male", 99, 1));
//        employees.add(new Employee(2, "Eric", 22, "male", 99, 1));
//        employees.add(new Employee(3, "Spongebob", 24, "male", 99, 1));
//        employees.add(new Employee(4, "Patrick", 22, "male", 99, 1));
//        employees.add(new Employee(5, "Gary", 24, "male", 99, 1));
//        employees.add(new Employee(6, "Squidward", 22, "male", 99, 1));
//
//        return employees;
//    }
//
//    public List<Company> getCompanies() {
//        return companies;
//    }
//
//    public Company findById(Integer companyId) {
//        return getCompanies()
//                .stream()
//                .filter(company -> company.getId().equals(companyId))
//                .findFirst()
//                .orElse(null);
//    }
//
//    public List<Employee> findEmployeesById(Integer companyId) {
//        return getCompanies()
//                .stream()
//                .filter(company -> company.getId().equals(companyId))
//                .findFirst()
//                .map(Company::getEmployees)
//                .orElse(null);
//    }
//
//    public List<Company> findByPageIndexAndPageSize(Integer pageIndex, Integer pageSize) {
//        return getCompanies()
//                .stream()
//                .skip((long) (pageIndex - 1) * pageSize)
//                .limit(pageSize).collect(Collectors.toList());
//    }
//
//    public Company addCompany(Company company) {
//        getCompanies().add(new Company(
//                getCompanies().size() + 1,
//                company.getCompanyName(),
//                company.getEmployees()));
//
//        return getCompanies().get(getCompanies().size() - 1);
//    }
//
//    public Company updateCompany(int companyId, Company companyToBeUpdated) {
//        return updateCompanyInfo(findById(companyId), companyToBeUpdated);
//    }
//
//    private Company updateCompanyInfo(Company company, Company companyToBeUpdated) {
//        if (companyToBeUpdated.getCompanyName() != null) {
//            company.setCompanyName(companyToBeUpdated.getCompanyName());
//        }
////        if (companyToBeUpdated.getEmployees() != null) {
////            company.setEmployees(companyToBeUpdated.getEmployees());
//      //      company.setEmployeesNumber(companyToBeUpdated.getEmployees().size());
////        }
//
//        return company;
//    }
//
//    public Boolean deleteCompany(int companyId) {
//        return getCompanies()
//                .removeIf(employee -> employee.getId().equals(companyId));
//    }
//}
