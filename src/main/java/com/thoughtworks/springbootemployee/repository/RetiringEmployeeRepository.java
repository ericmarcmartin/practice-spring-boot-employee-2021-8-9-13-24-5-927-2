//package com.thoughtworks.springbootemployee.repository;
//
//import com.thoughtworks.springbootemployee.entity.Employee;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Repository
//public class RetiringEmployeeRepository {
//
//    private final List<Employee> employees = new ArrayList<>();
//
//    public RetiringEmployeeRepository() {
//        employees.add(new Employee(1, "Francis", 24, "male", 99, 1));
//        employees.add(new Employee(2, "Eric", 22, "male", 99, 1));
//        employees.add(new Employee(3, "Spongebob", 24, "male", 99, 1));
//        employees.add(new Employee(4, "Patrick", 22, "male", 99, 1));
//        employees.add(new Employee(5, "Gary", 24, "male", 99, 1));
//        employees.add(new Employee(6, "Squidward", 22, "male", 99, 1));
//        employees.add(new Employee(7, "Pearl", 22, "male", 99, 1));
//    }
//
//    public List<Employee> getEmployees() {
//        return employees;
//    }
//
//    public Employee findById(Integer id) {
//        return getEmployees()
//                .stream()
//                .filter(employee -> employee.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//    }
//
//    public List<Employee> findByGender(String gender) {
//        return getEmployees()
//                .stream()
//                .filter(employee -> employee.getGender().equals(gender))
//                .collect(Collectors.toList());
//    }
//
//    public List<Employee> findByPageIndexAndPageSize(Integer pageIndex, Integer pageSize) {
//        return getEmployees()
//                .stream()
//                .skip((long) (pageIndex - 1) * pageSize)
//                .limit(pageSize).collect(Collectors.toList());
//    }
//
//    public Employee addEmployee(Employee employee) {
//        getEmployees().add(new Employee(
//                getEmployees().size() + 1,
//                employee.getName(),
//                employee.getAge(),
//                employee.getGender(),
//                employee.getSalary(),
//                employee.getCompanyId()));
//
//        return getEmployees().get(getEmployees().size() - 1);
//    }
//
//    public Employee updateEmployee(Integer id, Employee employeeToBeUpdated) {
//        return updateEmployeeInfo(findById(id), employeeToBeUpdated);
//    }
//
//    public Employee updateEmployeeInfo(Employee employee, Employee employeeToBeUpdated) {
//        if (employeeToBeUpdated.getAge() != null) {
//            employee.setAge(employeeToBeUpdated.getAge());
//        }
//        if (employeeToBeUpdated.getName() != null) {
//            employee.setName(employeeToBeUpdated.getName());
//        }
//        if (employeeToBeUpdated.getGender() != null) {
//            employee.setGender(employeeToBeUpdated.getGender());
//        }
//        if (employeeToBeUpdated.getSalary() != null) {
//            employee.setSalary(employeeToBeUpdated.getSalary());
//        }
//
//        return employee;
//    }
//
//    public boolean deleteEmployee(Integer id) {
//        return getEmployees()
//                .removeIf(employee -> employee.getId().equals(id));
//    }
//}
