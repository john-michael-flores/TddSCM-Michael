package com.week4day3.Week4Day3ExerciseTDD.service;

import com.week4day3.Week4Day3ExerciseTDD.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> findAllEmployees();
    Employee findEmployeeById(Long empId);
}
