package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/employees")
public
class EmployeesController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;


    @GetMapping
    public List<EmployeeResponse> getEmployees() {
        return employeeMapper.toResponse(employeeService.getAllEmployees());
    }

    @GetMapping(path = "/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Integer id) {
        return employeeMapper.toResponse(employeeService.getById(id));
    }

    @RequestMapping(params = "gender")
    public List<EmployeeResponse> getEmployeesByGender(@RequestParam String gender) {
        return employeeMapper.toResponse(employeeService.getByGender(gender));
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<EmployeeResponse> getEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return employeeMapper.toResponse(employeeService.getByPageIndexAndPageSize(pageIndex, pageSize));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return employeeMapper.toResponse(employeeService.create(employeeMapper.toEntity(employeeRequest)));
    }

    @PutMapping(path = "/{id}")
    public EmployeeResponse updateEmployee(@PathVariable Integer id, @RequestBody EmployeeRequest employeeRequest) {
        return employeeMapper.toResponse(employeeService.update(id, employeeMapper.toEntity(employeeRequest)));
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Integer id) {
        employeeService.delete(id);
    }
}
