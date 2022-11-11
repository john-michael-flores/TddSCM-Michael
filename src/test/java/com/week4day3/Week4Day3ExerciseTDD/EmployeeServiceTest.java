package com.week4day3.Week4Day3ExerciseTDD;


import com.week4day3.Week4Day3ExerciseTDD.model.Employee;
import com.week4day3.Week4Day3ExerciseTDD.repository.EmployeeRepository;
import com.week4day3.Week4Day3ExerciseTDD.service.EmployeeService;
import com.week4day3.Week4Day3ExerciseTDD.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    Employee kaneki, itachi;

    @BeforeEach
    void setup() {
        kaneki = new Employee();
        kaneki.setName("Kaneki");
        kaneki.setAge(22);
        kaneki.setSalary(100000d);
        kaneki.setPosition("Senior Software Engineer");

        itachi = new Employee();
        itachi.setName("Itachi");
        itachi.setAge(25);
        itachi.setSalary(30000d);
        itachi.setPosition("Junior Software Engineer");
    }

    @Test
    void saveEmployee() {
        //Arrange
        when(employeeRepository.save(any(Employee.class))).thenReturn(kaneki);

        //Act
        Employee newEmployee = employeeService.saveEmployee(kaneki);

        //Assert

        //Newly created employee should be not null
        assertNotNull(newEmployee);

        //Newly created employee id should auto generate; it should be not null
        assertThat(newEmployee.getSalary()).isEqualTo(100000d);
    }

    @Test
    void findAllEmployees() {
        //Arrange
        when(employeeRepository.findAll()).thenReturn(List.of(kaneki, itachi));

        //Act
        List<Employee> employeeList = employeeService.findAllEmployees();

        //Assert

        //Employee list should be not null
        assertNotNull(employeeList);

        //Employee list size should be 2
        assertEquals(2, employeeList.size());

        //Employee data from repository should match the employee list
        assertThat(employeeRepository.findAll()).containsAll(employeeList);
    }

    @Test
    void findEmployeeById() {
        //Arrange
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(itachi));

        //Act
        Employee employeeItachi = employeeService.findEmployeeById(2L);

        //Assert
        assertNotNull(employeeItachi);
        assertThat(employeeRepository.findById(2L)).contains(employeeItachi);
    }

    @Test
    void findEmployeeNotFound() {
        //Arrange
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        //Act

        //Assert
        assertThrows(ResponseStatusException.class, () -> employeeService.findEmployeeById(77L));
    }
}
