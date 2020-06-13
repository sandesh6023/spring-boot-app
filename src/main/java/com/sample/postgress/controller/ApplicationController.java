package com.sample.postgress.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.postgress.entity.Employee;
import com.sample.postgress.entity.Feedback;
import com.sample.postgress.entity.Sso;
import com.sample.postgress.entity.Users;
import com.sample.postgress.entity.Vote;
import com.sample.postgress.service.EmployeeService;

@RestController
@RequestMapping("/postgressApp")
public class ApplicationController {

//	@Resource 
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping(value = "/employeeList")
	public List<Employee> getEmployees() {
		return employeeService.findAll();
	
	}
	
	@GetMapping(value = "/Users")
	public List<Users> getUsers() {
		return employeeService.getAllsso();
	
	}
	
	@PostMapping(value = "/validatesso")
	public void createEmployee(@RequestBody Sso sso) {
		System.out.println("-------->"+sso.getSso());
		employeeService.validateAndUpdate(sso.getSso());
	}
	
	
	@PutMapping(value="/pollVotes")
	public void updateVotes(@RequestBody Vote vote) {
		employeeService.pollVotes(vote);
//		System.out.print("********"+vote.getEventId()+vote.getSso());
	}
	
	
	@GetMapping(value="/votes")
	public Vote getVotes(@RequestBody Vote vote) {
		return employeeService.getVotes(vote.getSso());
	}
	
	
	@PutMapping(value="/updatefeed")
	public void updateFeedback(@RequestBody Feedback feedback) {

		employeeService.saveFeedback(feedback);
		System.out.print("********"+feedback.getSso()+feedback.getFeedback());
	}
	
	@GetMapping(value="/feedback")
	public Feedback getFeedback(@RequestBody Feedback feedback) {
		return employeeService.getFeedback(feedback.getSso());
	}
		
	
	
//	@PostMapping(value = "/createEmp")
//	public void createEmployee(@RequestBody Employee emp) {
//		 employeeService.insertEmployee(emp);
//	
//	}
//	@PutMapping(value = "/updateEmp")
//	public void updateEmployee(@RequestBody Employee emp) {
//		 employeeService.updateEmployee(emp);
//	
//	}
//	@PutMapping(value = "/executeUpdateEmp")
//	public void executeUpdateEmployee(@RequestBody Employee emp) {
//		 employeeService.executeUpdateEmployee(emp);
//	
//	}
//	
//	@DeleteMapping(value = "/deleteEmpById")
//	public void deleteEmployee(@RequestBody Employee emp) {
//		 employeeService.deleteEmployee(emp);
//	
//	}
	
	
}
