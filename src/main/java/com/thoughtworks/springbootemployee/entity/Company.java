package com.thoughtworks.springbootemployee.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String companyName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId")
    List<Employee> employees = new ArrayList<>();

    public Company(Integer id, String companyName, List<Employee> employees) {
        this.id = id;
        this.companyName = companyName;
        this.employees = employees;
    }

    public Company(String companyName, List<Employee> employees) {
        this.companyName = companyName;
        this.employees = employees;
    }

    public Company() {

    }

    public Integer getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

}
