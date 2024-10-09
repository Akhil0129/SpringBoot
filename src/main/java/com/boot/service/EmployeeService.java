package com.boot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import java.sql.Timestamp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.advice.EmployeeNotFoundExeception;
import com.boot.dao.EmployeeRepository;
import com.boot.dao.LoginTableRepository;
import com.boot.entity.Employee;
import com.boot.entity.LoginTable;

import com.boot.model.EmployeeDTO;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private LoginTableRepository loginRepository;
	
	public  void deleteByEmail(String email) {
		employeeRepository.deleteByEmail(email);
	}
	
	public  EmployeeDTO findEmployeeByEmail(String email){
		Optional<Employee> optional=employeeRepository.findByEmail(email);
		if(optional.isPresent()) {
			EmployeeDTO employeeDTO=new EmployeeDTO();
			BeanUtils.copyProperties(optional.get(), employeeDTO);
			return employeeDTO;
		}else {
			throw new EmployeeNotFoundExeception("It seems like , this employee does not exist.");
		}
	}
	
	/**
	 *  This method is adding data inside database
	 * @param employee
	 */
	public  boolean addEmployee(EmployeeDTO employee ) {
		 Optional<Employee>optional=employeeRepository.findByEmail(employee.getEmail());
		if(optional.isPresent()) {
			return false;
		}
		Employee entity=new Employee();
		BeanUtils.copyProperties(employee, entity);
		employeeRepository.save(entity);
		return true;
	}
	
	public  List<EmployeeDTO> findEmployees(){
		 //FETCHING FROM DATA BASE
		 List<Employee> employeeEntitList=employeeRepository.findAll();
		 List<EmployeeDTO>  employeeDTOs=new ArrayList<EmployeeDTO>();
		 for(Employee entity : employeeEntitList) {
			 EmployeeDTO dto=new EmployeeDTO();
			 BeanUtils.copyProperties(entity, dto);
			 employeeDTOs.add(dto);
		 }
		 return employeeDTOs;
	}
	
	public  void updateEmployee(EmployeeDTO employee){
		Employee  dbEmployee=employeeRepository.findByEmail(employee.getEmail()).get();
		if(employee.getFirstName()!=null) {
			dbEmployee.setFirstName(employee.getFirstName());
		}
		if(employee.getLastName()!=null) {
			dbEmployee.setLastName(employee.getLastName());
		}
		if(employee.getPassword()!=null) {
			dbEmployee.setPassword(employee.getPassword());
		}
		employeeRepository.save(dbEmployee);
	}
	public  Long saveLogin(String  email ) {
		 Employee employee=employeeRepository.findByEmail(email).get();
		 LoginTable history=new LoginTable();
		history.setEmployee(employee);
		history.setLoginTime(new Timestamp(new Date().getTime()));
		LoginTable login=loginRepository.save(history);
		return login.getId();
	}
	@Transactional
	public  void updateLogoutTime(long llr) {
		LoginTable history=loginRepository.findById(llr).get();
		history.setLogoutTime(new Timestamp(new Date().getTime()));
	}
}
