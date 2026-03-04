package com.epam.employee_management.controller;

import com.epam.employee_management.exception.EmployeeNotFoundException;
import com.epam.employee_management.model.Employee;
import com.epam.employee_management.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee("John", "Doe", "john.doe@example.com",
                "Engineering", "Software Engineer", 75000.0);
        employee.setId(1L);
    }

    @Test
    void getAllEmployees_returnsOkWithList() throws Exception {
        List<Employee> employees = Arrays.asList(employee);
        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"));
    }

    @Test
    void getEmployeeById_existingId_returnsEmployee() throws Exception {
        when(employeeService.getEmployeeById(1L)).thenReturn(employee);

        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    void getEmployeeById_notFound_returns404() throws Exception {
        when(employeeService.getEmployeeById(99L))
                .thenThrow(new EmployeeNotFoundException(99L));

        mockMvc.perform(get("/api/employees/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createEmployee_validBody_returnsCreated() throws Exception {
        when(employeeService.createEmployee(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void createEmployee_invalidBody_returnsBadRequest() throws Exception {
        Employee invalid = new Employee();

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateEmployee_validBody_returnsOk() throws Exception {
        when(employeeService.updateEmployee(eq(1L), any(Employee.class))).thenReturn(employee);

        mockMvc.perform(put("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void deleteEmployee_existingId_returnsNoContent() throws Exception {
        mockMvc.perform(delete("/api/employees/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteEmployee_notFound_returns404() throws Exception {
        doThrow(new EmployeeNotFoundException(99L)).when(employeeService).deleteEmployee(99L);

        mockMvc.perform(delete("/api/employees/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllEmployees_withDepartmentFilter_returnsDepartmentList() throws Exception {
        when(employeeService.getEmployeesByDepartment("Engineering"))
                .thenReturn(Arrays.asList(employee));

        mockMvc.perform(get("/api/employees").param("department", "Engineering"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].department").value("Engineering"));
    }
}
