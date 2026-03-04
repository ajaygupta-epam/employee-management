package com.epam.employee_management.service;

import com.epam.employee_management.model.Employee;

import java.util.List;

/**
 * Service interface defining business operations for Employee management.
 */
public interface EmployeeService {

    /**
     * Retrieves all employees.
     *
     * @return list of all employees
     */
    List<Employee> getAllEmployees();

    /**
     * Retrieves an employee by their ID.
     *
     * @param id the employee's ID
     * @return the found employee
     */
    Employee getEmployeeById(Long id);

    /**
     * Creates a new employee.
     *
     * @param employee the employee to create
     * @return the created employee with generated ID
     */
    Employee createEmployee(Employee employee);

    /**
     * Updates an existing employee.
     *
     * @param id       the ID of the employee to update
     * @param employee the updated employee data
     * @return the updated employee
     */
    Employee updateEmployee(Long id, Employee employee);

    /**
     * Deletes an employee by their ID.
     *
     * @param id the employee's ID
     */
    void deleteEmployee(Long id);

    /**
     * Retrieves all employees in a given department.
     *
     * @param department the department name
     * @return list of employees in that department
     */
    List<Employee> getEmployeesByDepartment(String department);
}
