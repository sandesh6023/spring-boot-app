package com.sample.postgress.dao;

import java.util.List;

import com.sample.postgress.entity.Employee;


public interface EmployeeDao {

	List<Employee> findAll();
	
	void postCall(String sso);


}
