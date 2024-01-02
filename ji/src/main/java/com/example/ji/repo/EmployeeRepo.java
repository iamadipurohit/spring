package com.example.ji.repo;

import com.example.ji.model.Employee;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends CouchbaseRepository<Employee, String> {

}