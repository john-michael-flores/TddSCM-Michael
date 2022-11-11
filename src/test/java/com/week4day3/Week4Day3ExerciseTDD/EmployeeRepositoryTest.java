package com.week4day3.Week4Day3ExerciseTDD;

import com.week4day3.Week4Day3ExerciseTDD.model.Employee;
import com.week4day3.Week4Day3ExerciseTDD.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

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
    void save() {
        //Arrange

        //Act
        Employee newEmployeeKaneki = employeeRepository.save(kaneki);

        //Assert

        //Newly created employee should be not null
        assertNotNull(newEmployeeKaneki);

        //Newly created employee id should auto generate; it should be not null
        assertThat(newEmployeeKaneki.getId()).isNotEqualTo(null);
    }

    @Test
    void findAll() {
        //Arrange

        //Act
        List<Employee> employeeList = employeeRepository.saveAll(List.of(kaneki, itachi));

        //Assert

        //Employee list should be not null
        assertNotNull(employeeList);

        //Employee list size should be 2
        assertEquals(2, employeeList.size());

        //Employee data from repository should match the employee list
        assertThat(employeeRepository.findAll()).containsAll(employeeList);
    }

    @Test
    void findById() {
        //Arrange

        //Act
        Employee employeeKaneki = employeeRepository.save(kaneki);
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeKaneki.getId());

        //Assert
        assertTrue(employeeOptional.isPresent());
        assertEquals(1L, employeeKaneki.getId());
        assertThat(employeeOptional).contains(kaneki);
    }
}


