package com.epam.employee_management.controller;

import com.epam.employee_management.model.Employee;
import com.epam.employee_management.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing employees.
 * Provides CRUD endpoints under /api/employees.
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Retrieves all employees, optionally filtered by department.
     *
     * @param department optional department filter
     * @return list of employees
     */
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(
            @RequestParam(required = false) String department) {
        if (department != null && !department.isBlank()) {
            return ResponseEntity.ok(employeeService.getEmployeesByDepartment(department));
        }
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    /**
     * Retrieves a single employee by ID.
     *
     * @param id the employee ID
     * @return the employee
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    /**
     * Creates a new employee.
     *
     * @param employee the employee to create
     * @return the created employee with HTTP 201 status
     */
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee created = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Updates an existing employee.
     *
     * @param id       the ID of the employee to update
     * @param employee the updated employee data
     * @return the updated employee
     */
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employee));
    }

    /**
     * Deletes an employee by ID.
     *
     * @param id the employee ID
     * @return HTTP 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
