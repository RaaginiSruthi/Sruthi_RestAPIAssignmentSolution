package com.gl.springboot.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.springboot.rest.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	List<Employee> findByFirstNameContainsAllIgnoreCase(String firstName);

	List<Employee> findAllEqualsIgnoreCaseByOrderByFirstNameDesc();

	List<Employee> findAllEqualsIgnoreCaseByOrderByFirstNameAsc();

}
