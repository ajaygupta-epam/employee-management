package com.epam.employee_management.controller;

import com.epam.employee_management.model.Employee;
import com.epam.employee_management.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for EmployeeController.
 * Validates the POST /api/employees endpoint.
 */
@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createEmployee_shouldReturnCreatedEmployee() throws Exception {
        Employee inputEmployee = new Employee("Ajay Gupta", "Lead Engineer");

        Employee savedEmployee = new Employee("Ajay Gupta", "Lead Engineer");
        savedEmployee.setId(1L);

        when(employeeService.createEmployee(any(Employee.class)))
            .thenReturn(savedEmployee);

        mockMvc.perform(post("/api/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputEmployee)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.employeeName").value("Ajay Gupta"))
            .andExpect(jsonPath("$.employeeDesignation")
                .value("Lead Engineer"));
    }
}
