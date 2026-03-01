package com.epam.employee_management.service;

import com.epam.employee_management.model.Employee;
import com.epam.employee_management.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

/**
 * Service layer for managing Employee operations.
 * Encapsulates business logic for employee creation.
 */
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * Constructor injection for EmployeeRepository.
     *
     * @param employeeRepository the employee repository
     */
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Creates and stores a new employee record in the database.
     *
     * @param employee the employee details to persist
     * @return the saved employee with generated ID
     */
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
