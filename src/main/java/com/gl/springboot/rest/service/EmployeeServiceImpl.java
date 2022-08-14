package com.gl.springboot.rest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gl.springboot.rest.entity.Employee;
import com.gl.springboot.rest.entity.Role;
import com.gl.springboot.rest.entity.User;
import com.gl.springboot.rest.repository.EmployeeRepository;
import com.gl.springboot.rest.repository.RoleRepository;
import com.gl.springboot.rest.repository.UserRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	private RoleRepository roleRepository;

	private UserRepository userRepository;

	private BCryptPasswordEncoder bcryptEncoder;

	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository, RoleRepository theRoleRepository,
			UserRepository theUserRepository, BCryptPasswordEncoder theBcryptEncoder) {
		employeeRepository = theEmployeeRepository;
		roleRepository = theRoleRepository;
		userRepository = theUserRepository;
		bcryptEncoder = theBcryptEncoder;
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);

		Employee theEmployee = null;
		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			throw new RuntimeException("Did not find employee id - " + theId);
		}

		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

	@Override
	public List<Employee> searchByFirstName(String firstName) {
		return employeeRepository.findByFirstNameContainsAllIgnoreCase(firstName);
	}

	@Override
	public List<Employee> sortByFirstNameDesc() {
		return employeeRepository.findAllEqualsIgnoreCaseByOrderByFirstNameDesc();
	}

	@Override
	public List<Employee> sortByFirstNameAsc() {
		return employeeRepository.findAllEqualsIgnoreCaseByOrderByFirstNameAsc();
	}

	@Override
	public User saveUser(User user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}

}
