package com.boot.model;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EmployeeDTO {
	private Long id;
	
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 5, max = 30, message = "FirstName must be between 2 and 30 characters")
	private String firstName;
	private String lastName;
	@NotBlank(message = "Email cannot be blank")
	@Email(message = "Email should be valid")
	private String email;
	
	private String password = "jill";
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String image;
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EmployeeDTO() {
	}
	
	public EmployeeDTO(String email,String password) {
		this.email=email;
		this.password=password;
	}

	@Override
	public boolean equals(Object obj) {
		EmployeeDTO other = (EmployeeDTO) obj;
		return email.equalsIgnoreCase(other.getEmail()) && password.equalsIgnoreCase(other.getPassword());
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public EmployeeDTO(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

}
