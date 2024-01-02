package com.example.ji.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import com.example.ji.repo.EmployeeRepo;
import com.example.ji.model.Employee;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Sort;

public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    // Existing service methods...
    public Employee updateEmployee(Employee employee) {
        Employee existingemployee = employeeRepo.findById(employee.getId()).orElse(null);

        existingemployee.setEmployeeName(employee.getEmployeeName());
        existingemployee.setPhoneNumber(employee.getPhoneNumber());
        existingemployee.setPhoneNumber(employee.getPhoneNumber());
        return employeeRepo.save(existingemployee);
    }

    public Employee getNthLevelManager(String id, int level) {
        Employee curr = employeeRepo.findById(id).orElse(null);
        for (int i = 0; i < level; i++) {
            String reportto = curr.getReportsTo();
            curr = employeeRepo.findById(reportto).orElse(null);
        }
        return curr;
    }

    public Page<Employee> getAllEmployeesWithPaginationAndSorting(int page, int size, String[] sort) {
        Sort sortObj = Sort.by(sort);
        return employeeRepo.findAll(PageRequest.of(page, size, sortObj));
    }
}
