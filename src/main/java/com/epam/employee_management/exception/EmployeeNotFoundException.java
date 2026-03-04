package com.epam.employee_management.exception;

/**
 * Exception thrown when an employee is not found by the given identifier.
 */
public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id) {
        super("Employee not found with id: " + id);
    }
}
