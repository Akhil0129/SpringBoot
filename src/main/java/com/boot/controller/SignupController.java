package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.model.EmployeeDTO;
import com.boot.service.EmployeeService;

@Controller
public class SignupController {
	
	@Autowired
	private EmployeeService employeeService;

	//<form action="cauth" method="get">
	@GetMapping("/signup")
	public String showSignup() {
		return "signup";  // signup.jsp
	}
	
	@PostMapping("/signup")
	public String postMapping(@ModelAttribute EmployeeDTO employee,Model model) {
		employeeService.addEmployee(employee);
		model.addAttribute("message","Hey! registration is done");
		return "auth";  // auth.jsp
	}
	
	//editEmployee ? email= 
	@GetMapping("/editEmployee")
	public String ecditEmployee(String email,Model model) {
		EmployeeDTO employee=employeeService.findEmployeeByEmail(email);
		model.addAttribute("employee", employee);
		return "esignup";  // esignup.jsp
	}
	
	@PostMapping("/updateSignup")
	public String updateSignup(@ModelAttribute EmployeeDTO employee,Model model) {
		employeeService.updateEmployee(employee);
		System.out.println(employee);
		model.addAttribute("message", "Employee is updated successfully email = "+employee.getEmail());
		List<EmployeeDTO> employees=employeeService.findEmployees();
		model.addAttribute("employees", employees);
		return "home";  // esignup.jsp
	}
	
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam String email,Model model) {
		employeeService.deleteByEmail(email);
		List<EmployeeDTO> employees=employeeService.findEmployees();
		//adding into request scope
		model.addAttribute("message", "Employee is deleted successfully email = "+email);
		model.addAttribute("employees", employees);
		return "home";  // auth.jsp
	}
	
}
