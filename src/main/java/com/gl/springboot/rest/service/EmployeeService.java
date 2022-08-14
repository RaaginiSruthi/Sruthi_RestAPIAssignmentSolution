package com.gl.springboot.rest.service;

import java.util.List;

import com.gl.springboot.rest.entity.Employee;
import com.gl.springboot.rest.entity.Role;
import com.gl.springboot.rest.entity.User;

public interface EmployeeService {

	public List<Employee> findAll();

	public Employee findById(int theId);

	public void save(Employee theEmployee);

	public void deleteById(int theId);

	public List<Employee> searchByFirstName(String firstName);

	public List<Employee> sortByFirstNameAsc();
	
	public List<Employee> sortByFirstNameDesc();

	public User saveUser(User user);

	public Role saveRole(Role role);
}
