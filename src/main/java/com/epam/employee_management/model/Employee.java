package com.epam.employee_management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity representing an Employee record stored in H2 database.
 * Contains employee ID, name, and designation.
 */
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_name", nullable = false)
    private String employeeName;

    @Column(name = "employee_designation", nullable = false)
    private String employeeDesignation;

    /** Default constructor required by JPA. */
    public Employee() {
    }

    /**
     * Parameterized constructor for creating Employee instances.
     *
     * @param employeeName        the name of the employee
     * @param employeeDesignation the designation of the employee
     */
    public Employee(String employeeName, String employeeDesignation) {
        this.employeeName = employeeName;
        this.employeeDesignation = employeeDesignation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeDesignation() {
        return employeeDesignation;
    }

    public void setEmployeeDesignation(String employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
    }

    @Override
    public String toString() {
        return "Employee{"
            + "id=" + id
            + ", employeeName='" + employeeName + '\''
            + ", employeeDesignation='" + employeeDesignation + '\''
            + '}';
    }
}
