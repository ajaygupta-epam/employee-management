package com.epam.employee_management.service;

import com.epam.employee_management.exception.EmployeeNotFoundException;
import com.epam.employee_management.model.Employee;
import com.epam.employee_management.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee("John", "Doe", "john.doe@example.com",
                "Engineering", "Software Engineer", 75000.0);
        employee.setId(1L);
    }

    @Test
    void getAllEmployees_returnsAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));

        List<Employee> result = employeeService.getAllEmployees();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    void getEmployeeById_existingId_returnsEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployeeById(1L);

        assertThat(result.getFirstName()).isEqualTo("John");
    }

    @Test
    void getEmployeeById_nonExistingId_throwsException() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.getEmployeeById(99L))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void createEmployee_savesAndReturnsEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee result = employeeService.createEmployee(employee);

        assertThat(result.getId()).isEqualTo(1L);
        verify(employeeRepository).save(employee);
    }

    @Test
    void updateEmployee_existingId_updatesAndReturnsEmployee() {
        Employee updated = new Employee("Jane", "Doe", "jane.doe@example.com",
                "HR", "HR Manager", 80000.0);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee result = employeeService.updateEmployee(1L, updated);

        assertThat(result).isNotNull();
        verify(employeeRepository).save(employee);
    }

    @Test
    void deleteEmployee_existingId_deletesEmployee() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1L);

        verify(employeeRepository).delete(employee);
    }

    @Test
    void getEmployeesByDepartment_returnsDepartmentEmployees() {
        when(employeeRepository.findByDepartment("Engineering"))
                .thenReturn(Arrays.asList(employee));

        List<Employee> result = employeeService.getEmployeesByDepartment("Engineering");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getDepartment()).isEqualTo("Engineering");
    }
}
