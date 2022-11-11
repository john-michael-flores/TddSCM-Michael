package com.week4day3.Week4Day3ExerciseTDD;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.week4day3.Week4Day3ExerciseTDD.model.Employee;
import com.week4day3.Week4Day3ExerciseTDD.service.EmployeeServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;


import static org.mockito.ArgumentMatchers.any;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class EmployeeControllerTest {

    @MockBean
    private EmployeeServiceImpl employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
    void saveEmployee() throws Exception {
        //Arrange
        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(kaneki);

        //Act
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(kaneki)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(kaneki.getName())))
                .andExpect(jsonPath("$.age", CoreMatchers.is(kaneki.getAge())))
                .andExpect(jsonPath("$.salary", CoreMatchers.is(kaneki.getSalary())))
                .andExpect(jsonPath("$.position", CoreMatchers.is(kaneki.getPosition())));

    }

    @Test
    void getAllEmployees() throws Exception {
        //Arrange
        when(employeeService.findAllEmployees()).thenReturn(List.of(kaneki, itachi));

        //Act
        mockMvc.perform(get("/employees")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(List.of(kaneki, itachi).size())))
                .andExpect(jsonPath("$.[0].name", CoreMatchers.is(kaneki.getName())))
                .andExpect(jsonPath("$.[1].name", CoreMatchers.is(itachi.getName())));
    }

    @Test
    void getEmployeeById() throws Exception {
        //Arrange
        when(employeeService.findEmployeeById(anyLong())).thenReturn(kaneki);

        //Act
        mockMvc.perform(get("/employees/{empId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(kaneki.getName())));
    }
}
