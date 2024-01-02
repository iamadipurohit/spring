package com.example.ji.controller;

import com.example.ji.model.Employee;
import com.example.ji.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ji.service.PersonService;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/employees")

public class EmpController {
    @Autowired
    private EmployeeRepo personRepository;

    // a. Add Employee to a Database
    @PostMapping("/addEmployee")
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
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return personRepository.findAll();
    }

    // c. Delete Employee by ID
    @DeleteMapping("/delete/{id}")
    public void deleteEmployeeById(@PathVariable String id) {
        personRepository.deleteById(id);
    }

    // d. Update Employee by ID
    @PutMapping("/{id}")
    public Employee updateEmployeeById(@PathVariable String id, @RequestBody Employee updatedEmployee) {
        updatedEmployee.setId(id);
        return personRepository.save(updatedEmployee);
    }

    @GetMapping("/manager/{personId}/{level}")
    public ResponseEntity<Employee> getNthLevelManager(
            @PathVariable String id,
            @PathVariable int level) {
        Employee manager = personService.getNthLevelManager(id, level);
        if (manager != null) {
            return new ResponseEntity<>(manager, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<Page<Employee>> getAllPersons(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "personName,asc") String[] sort) {
        Page<Employee> persons = personService.getAllEmployeesWithPaginationAndSorting(page, size, sort);

        return new ResponseEntity<>(persons, HttpStatus.OK);
    }
}