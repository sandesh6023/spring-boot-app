package com.sample.postgress.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.postgress.entity.Employee;
import com.sample.postgress.entity.Sso;
import com.sample.postgress.service.EmployeeService;


@RestController
@RequestMapping("/postgressApp")
public class ApplicationController {


	@Autowired
	EmployeeService employeeService;
	
	
	@PostMapping(value = "/validatessso")
	public void postExample(@RequestBody Sso sso) {
		System.out.println("-------->"+sso.getSso());
		employeeService.postCall(sso.getSso());
	}
	
	
	@GetMapping(value = "/employeeList")
	public List<Employee> getEmployees() {
		return employeeService.findAll();
	
	}
	
	
	@RequestMapping(value = "/branch/{branchname}", method = RequestMethod.GET, produces = "application/json", consumes = "application/json", headers = "Accept=application/json")
	public void getBranchname(@PathVariable("branchname") String branch ) {	
		System.out.print("----->"+branch);
//		return iPunchlistDashboardService.getPunchListDashboard(punchlistdashboarddropDownDTO);
	}

	
	
	
}
