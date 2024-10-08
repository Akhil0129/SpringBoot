package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.model.EmployeeDTO;
import com.boot.service.EmployeeService;

@Controller
public class AuthController {

	@Autowired
	private EmployeeService employeeService;

	// <form action="cauth" method="get">
	@GetMapping({"/cauth","/"})
	public String showAuthJsp(@RequestParam(required = false) String logout,Model model) {
		if(logout!=null) {
			model.addAttribute("message", "You have logged out successfully!");
		}
		return "auth"; // auth.jsp
	}

	@PostMapping("/cauth")
	public String postAuth(@RequestParam String email, @RequestParam String ppassword, Model model) {
		if (!validateEmail(email)) {
			model.addAttribute("message", "Hey your email is not valid!");
			return "auth";
		}
		EmployeeDTO employee = new EmployeeDTO(email, ppassword);
		if (employeeService.findEmployees().contains(employee)) {
			model.addAttribute("message", "User is there, Thank you!");
			// I am setting this messasge inside request scope
			// so that I can pick it on jsp
			// loading employee
			List<EmployeeDTO> employees = employeeService.findEmployees();
			// adding into request scope
			model.addAttribute("employees", employees);
			return "home";
		} else {
			model.addAttribute("message", "User is not there, Sorry!!");
			// I am setting this messasge inside request scope
			// so that I can pick it on jsp
			return "auth";
		}
	}


	private boolean validateEmail(String input) {
		return input.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
	}

}
