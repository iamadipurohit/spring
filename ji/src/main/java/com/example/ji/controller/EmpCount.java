package com.example.ji.controller;

import com.example.ji.model.Employee;
import com.example.ji.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")

public class EmpCount {
    @Autowired
    private EmployeeRepo personRepository;

    // a. Add Employee to a Database
    @PostMapping
    public String addEmployee(@RequestBody Employee person) {
        // Generate unique UUID as the ID for the Employee
        String employeeId = UUID.randomUUID().toString();
        person.setId(employeeId);

        // Save the employee to the database
        personRepository.save(person);

        // Return the generated ID
        return employeeId;
    }

    // b. Get All Employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return personRepository.findAll();
    }

    // c. Delete Employee by ID
    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable String id) {
        personRepository.deleteById(id);
    }

    // d. Update Employee by ID
    @PutMapping("/{id}")
    public Employee updateEmployeeById(@PathVariable String id, @RequestBody Employee updatedEmployee) {
        updatedEmployee.setId(id);
        return personRepository.save(updatedEmployee);
    }

}