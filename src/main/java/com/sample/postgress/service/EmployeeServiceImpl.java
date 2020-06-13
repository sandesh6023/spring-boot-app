package com.sample.postgress.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sample.postgress.dao.EmployeeDao;
import com.sample.postgress.entity.Employee;
import com.sample.postgress.entity.Feedback;
import com.sample.postgress.entity.Users;
import com.sample.postgress.entity.Vote;
@Component
public class EmployeeServiceImpl implements EmployeeService{
	@Resource 
	EmployeeDao employeeDao;
	@Override
	public List<Employee> findAll() {
		return employeeDao.findAll();
	}
	@Override
	public List<Users> getAllsso() {
		return employeeDao.getAllsso();
	}
	
	@Override
	public void validateAndUpdate(String sso) {
		 employeeDao.validateAndUpdate(sso);
	}
	
	@Override
	public void pollVotes(Vote vote) {
		employeeDao.pollVotes(vote);
	}
	@Override
	public Vote getVotes(String sso) {
		return employeeDao.getVotes(sso);
	}
	@Override
	public void saveFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		employeeDao.saveFeedback(feedback);
		
	}
	@Override
	public Feedback getFeedback(String sso) {
		return employeeDao.getFeedback(sso);
		
	}
	
	
//	@Override
//	public void insertEmployee(Employee emp) {
//		employeeDao.insertEmployee(emp);
//		
//	}
//	@Override
//	public void updateEmployee(Employee emp) {
//		employeeDao.updateEmployee(emp);
//		
//	}
//	@Override
//	public void executeUpdateEmployee(Employee emp) {
//		employeeDao.executeUpdateEmployee(emp);
//		
//	}
//
//	@Override
//	public void deleteEmployee(Employee emp) {
//		employeeDao.deleteEmployee(emp);
//		
//	}
}
