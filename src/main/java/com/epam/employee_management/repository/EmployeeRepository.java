package com.epam.employee_management.repository;

import com.epam.employee_management.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Employee entity.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Find employees by department.
     *
     * @param department the department name
     * @return list of employees in the department
     */
    List<Employee> findByDepartment(String department);

    /**
     * Find an employee by email.
     *
     * @param email the employee's email address
     * @return an Optional containing the employee if found
     */
    Optional<Employee> findByEmail(String email);
}
