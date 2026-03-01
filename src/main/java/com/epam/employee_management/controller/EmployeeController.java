package com.epam.employee_management.controller;

import com.epam.employee_management.model.Employee;
import com.epam.employee_management.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for Employee operations.
 * Provides endpoint to create and store employee details.
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * Constructor injection for EmployeeService.
     *
     * @param employeeService the employee service
     */
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Creates a new employee record.
     *
     * POST /api/employees
     *
     * @param employee the employee details from request body
     * @return the created employee with HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<Employee> createEmployee(
            @RequestBody Employee employee) {
        Employee savedEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }
}
