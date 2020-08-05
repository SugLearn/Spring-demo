package com.example.angularspringdemo.repository;

import com.example.angularspringdemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Employee findByFirstName(String username);
}
