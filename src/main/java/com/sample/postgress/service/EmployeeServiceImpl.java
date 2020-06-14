package com.sample.postgress.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sample.postgress.dao.EmployeeDao;
import com.sample.postgress.entity.Employee;

@Component
public class EmployeeServiceImpl implements EmployeeService{
	@Resource 
	EmployeeDao employeeDao;
	
	
	@Override
	public void postCall(String sso1) {
		System.out.print("here---------->inside service postCall");
		employeeDao.postCall(sso1);
	}
	
	@Override
	public List<Employee> findAll() {
		System.out.print("here---------->inside service findAll");
		return employeeDao.findAll();
	}
	
	

}
