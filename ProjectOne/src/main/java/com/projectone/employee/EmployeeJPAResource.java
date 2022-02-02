package com.projectone.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class EmployeeJPAResource {
	
	@Autowired
	private EmployeeRepository emp;
	
	@GetMapping("/employees")
	public List<Employee> retrieveAllEmployees(){
		return emp.findAll();
	}
	
	@PostMapping("/register")
	public Employee registerEmployee(@RequestBody Employee employee) {
		Employee newEmployee = emp.save(employee);
		return newEmployee;
		
	}
	@PostMapping("/login")
	public List<Employee> employeelogin(@RequestBody Employee employee) {
		String usernameJs = employee.getUsername();
		List<Employee> userFromDatabase = emp.findByUsername(usernameJs);
		
		return userFromDatabase;
		
	}

}
