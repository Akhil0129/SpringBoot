package com.boot.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.boot.dao.EmployeeRepository;
import com.boot.model.EmployeeDTO;
import com.boot.service.EmployeeService;

@Controller
public class SignupController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeRepository employeeRepository;


	//<form action="cauth" method="get">
	@GetMapping("/signup")
	public String showSignup() {
		return "signup";  // signup.jsp
	}
	
	@PostMapping("/signup")
	public String postMapping(@Valid @ModelAttribute EmployeeDTO employee,MultipartFile file,BindingResult result,Model model) {
		// If there are validation errors, return to the form page
        if (result.hasErrors()) {
        	model.addAttribute("message","Validation error.");
            return "auth";
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
			return "auth";
		}
		try {
			employee.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean status=employeeService.addEmployee(employee);
		if(status) {
			model.addAttribute("message","Hey! registration is done");
		}
		else {
        	model.addAttribute("message","email already exists");
			return "signup";
        }
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
	
	@GetMapping("/sortByEmail")
	public String sortEmployee(@RequestParam String orderBy,Model model) {
	
		 List<EmployeeDTO> employees=employeeService.findEmployees();
		 //adding into request scope
		 if(orderBy.equals("asc")) {
		   model.addAttribute("corderBy", "desc"); 
		   Collections.sort(employees,Comparator.comparing(EmployeeDTO::getEmail));
		 }else {
			 model.addAttribute("corderBy", "asc"); 
			 Collections.sort(employees,Comparator.comparing(EmployeeDTO::getEmail).reversed());
		 }
		 model.addAttribute("employees", employees);
		return "home";
	}
	
}
