package com.sample.postgress.service;

import java.util.List;

import com.sample.postgress.entity.Employee;
import com.sample.postgress.entity.Feedback;
import com.sample.postgress.entity.Users;
import com.sample.postgress.entity.Vote;

public interface EmployeeService {
	List<Employee> findAll();
	
	List<Users> getAllsso();
	
	void validateAndUpdate(String sso);
	
	void pollVotes(Vote vote);
	
	Vote getVotes(String sso);
	
	void saveFeedback(Feedback feedback);
	
	Feedback getFeedback(String sso);

//	void insertEmployee(Employee emp);
//
//	void updateEmployee(Employee emp);
//
//	void executeUpdateEmployee(Employee emp);
//
//	void deleteEmployee(Employee emp);
	
}
