package com.sample.postgress.service;

import java.util.List;

import com.sample.postgress.entity.Employee;

public interface EmployeeService {
	List<Employee> findAll();
	
	void postCall(String sso1);

}
