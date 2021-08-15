package com.thoughtworks.springbootemployee.dto;

import javax.persistence.JoinColumn;


public class EmployeeRequest {

    public String name;
    public Integer age;
    public String gender;
    public Integer salary;

    @JoinColumn(insertable = false, updatable = false)
    public Integer companyId;

    public EmployeeRequest() {
    }

    public EmployeeRequest(Integer id, String name, Integer age, String gender, Integer salary, Integer companyId) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.companyId = companyId;
    }

    public EmployeeRequest(Integer id, String name, Integer age, String gender, Integer salary) {
        this(id, name, age, gender, salary, null);
    }

    public EmployeeRequest(String name, Integer age, String gender, Integer salary, Integer companyId) {
        this(null, name, age, gender, salary, companyId);
    }

    public EmployeeRequest(String name, Integer age, String gender, Integer salary) {
        this(name, age, gender, salary, null);
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

}
