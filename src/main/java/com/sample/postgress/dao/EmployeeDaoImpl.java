package com.sample.postgress.dao;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.sample.postgress.entity.Employee;


@Repository
public class EmployeeDaoImpl implements EmployeeDao{


	@Override
	public void postCall(String sso) {
		System.out.print("here---------->inside dao postCall");
		
	}
	
	@Override
	public List<Employee> findAll() {
		System.out.print("---->inside dao findall");
		
		return null;
	}
	
}
