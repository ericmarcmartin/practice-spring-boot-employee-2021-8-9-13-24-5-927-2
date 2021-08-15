package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.util.Collections.emptyList;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    void should_return_all_companies_when_call_get_companies_api() throws Exception {
        // given
        Company company = new Company("Alibaba", emptyList());
        companyRepository.save(company);

        // when
        // then
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].companyName").value("Alibaba"))
                .andExpect(jsonPath("$[0].employees").isArray());
    }

    @Test
    public void should_return_company_when_call_get_company_by_id_api_given_company_id() throws Exception {
        // given
        Company company = new Company("Alibaba", emptyList());
        Integer returnedCompanyId = companyRepository.save(company).getId();

        // when
        // then
        mockMvc.perform(get(format("/companies/%d", returnedCompanyId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.companyName").value("Alibaba"))
                .andExpect(jsonPath("$.employees").isArray());
    }

    @Test
    public void should_return_employees_when_call_get_api_employees_given_company_id() throws Exception {
        // given
        Company company = new Company("Alibaba", emptyList());
        Integer returnedCompanyId = companyRepository.save(company).getId();
        employeeRepository.save(new Employee(1, "Francis", 24, "male", 999, returnedCompanyId));

        // when
        // then
        mockMvc.perform(get(format("/companies/%d/employees", returnedCompanyId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].companyId").value(returnedCompanyId));
    }

    @Test
    public void should_return_not_found_status_when_call_get_company_by_id_api_given_not_existing_company_id() throws Exception {
        // given
        Integer wrongCompanyId = 0;

        // when
        // then
        mockMvc.perform(get(format("/companies/%d", wrongCompanyId)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message")
                        .value(format("Company ID %s not found.", wrongCompanyId)));
    }

    @Test
    public void should_return_companies_when_call_get_companies_api_given_page_index_and_page_size() throws Exception {
        // given
        Integer pageIndex = 1;
        Integer pageSize = 1;

        Integer returnedCompanyId = companyRepository.save(new Company("Alibaba", emptyList())).getId();
        companyRepository.save(new Company("Shoppee", emptyList()));

        // when
        // then
        mockMvc.perform(get(format("/companies?pageIndex=%d&pageSize=%d", pageIndex, pageSize)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].companyName").value("Alibaba"))
                .andExpect(jsonPath("$[0].id").value(returnedCompanyId));
    }

    @Test
    public void should_create_company_when_call_create_api_given_company_request() throws Exception {
        // given
        employeeRepository.save(employeesDataFactory().get(0));
        String employeeJson = "{\n" +
                "        \"companyName\": \"Wallmart\"\n" +
                "    }";

        // when
        // then
        mockMvc.perform(post("/companies")
                .contentType(APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.companyName").value("Wallmart"));
    }

    @Test
    public void should_return_updated_company_when_call_update_given_company_id_and_company_request() throws Exception {
        // given
        Company company = new Company("Alibaba", emptyList());
        Integer returnedCompanyId = companyRepository.save(company).getId();

        Employee employee = new Employee("Spongebob", 23, "male", 999, returnedCompanyId);
        Integer returnedEmployeeId = employeeRepository.save(employee).getId();

        String employeeJson = "\n" +
                "    {\n" +
                "        \"companyName\": \"Chum Bucket\"\n" +
                "    }\n";

        // when
        // then
        mockMvc.perform(put(format("/companies/%d", returnedCompanyId))
                .contentType(APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(returnedCompanyId))
                .andExpect(jsonPath("$.companyName").value("Chum Bucket"))
                .andExpect(jsonPath("$.employees[0].id").value(returnedEmployeeId))
                .andExpect(jsonPath("$.employees[0].companyId").value(returnedCompanyId));
    }

    @Test
    public void should_remove_company_when_call_delete_company_api_given_company_id() throws Exception {
        // given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Spongebob", 22, "Male", 99, 1));

        Company company = new Company("Krusty Krabs", employees);
        Integer returnedCompanyId = companyRepository.save(company).getId();

        // when
        // then
        mockMvc.perform(delete(format("/companies/%d", returnedCompanyId)))
                .andExpect(status().isOk());

        mockMvc.perform(get(format("/companies/%d", returnedCompanyId)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(format("Company ID %d not found.", returnedCompanyId)));
    }

    @Test
    public void should_return_not_found_status_when_call_delete_company_by_id_api_given_not_existing_company_id() throws Exception {
        // given
        // when
        // then
        mockMvc.perform(delete(format("/companies/%d", 0)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(format("Company ID %d not found.", 0)));
    }

    private List<Company> companiesDataFactory() {
        List<Company> companies = new ArrayList<>();
        List<Employee> employees1 = new ArrayList<>();
        employees1.add(employeesDataFactory().get(0));
        employees1.add(employeesDataFactory().get(1));
        employees1.add(employeesDataFactory().get(2));


        List<Employee> employees2 = new ArrayList<>();
        employees2.add(employeesDataFactory().get(3));
        employees2.add(employeesDataFactory().get(4));
        employees2.add(employeesDataFactory().get(5));

        companies.add(new Company(1, "Alibaba", null));
        companies.add(new Company(2, "Shoppee", null));

        return companies;
    }

    private List<Employee> employeesDataFactory() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Francis", 24, "male", 99));
        employees.add(new Employee(2, "Eric", 22, "male", 99));
        employees.add(new Employee(3, "Spongebob", 24, "male", 99));
        employees.add(new Employee(4, "Patrick", 22, "male", 99));
        employees.add(new Employee(5, "Gary", 24, "male", 99));
        employees.add(new Employee(6, "Squidward", 22, "male", 99));
        employees.add(new Employee("Sandy", 22, "female", 99, 1));

        return employees;
    }
}
