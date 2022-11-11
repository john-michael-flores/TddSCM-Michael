package com.week4day3.Week4Day3ExerciseTDD.repository;

import com.week4day3.Week4Day3ExerciseTDD.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
