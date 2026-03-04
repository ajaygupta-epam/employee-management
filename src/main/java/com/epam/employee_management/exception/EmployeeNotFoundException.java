package com.epam.employee_management.exception;

/**
 * Exception thrown when an employee is not found in the system.
 */
public class EmployeeNotFoundException extends RuntimeException {

    /**
     * Constructs an EmployeeNotFoundException with the given employee ID.
     *
     * @param id the ID of the employee that was not found
     */
    public EmployeeNotFoundException(Long id) {
        super("Employee not found with id: " + id);
    }

    /**
     * Constructs an EmployeeNotFoundException with a custom message.
     *
     * @param message the detail message
     */
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
