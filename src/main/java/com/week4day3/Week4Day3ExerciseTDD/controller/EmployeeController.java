package com.week4day3.Week4Day3ExerciseTDD.controller;

import com.week4day3.Week4Day3ExerciseTDD.model.Employee;
import com.week4day3.Week4Day3ExerciseTDD.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/{empId}")
    public Employee getEmployeeById(@PathVariable Long empId) {
        return employeeService.findEmployeeById(empId);
    }
}
