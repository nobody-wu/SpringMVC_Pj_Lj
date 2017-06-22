package com.lj.springmvc.crud.entities;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

public class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -156144842647105926L;

	private Integer id;
	@NotEmpty
	private String lastName;
	
	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birth;
	@NumberFormat(pattern="#,###,###.#")
	private Float salary;
	
	public Employee(Integer id, String lastName, Date birth, Float salary,
			String email, Integer gender, Department department) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.birth = birth;
		this.salary = salary;
		this.email = email;
		this.gender = gender;
		this.department = department;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	@Email
	private String email;
	private Integer gender;
	private Department department;
	
	public Employee()
	{
		this.department=new Department();
	}
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", birth="
				+ birth + ", salary=" + salary + ", email=" + email
				+ ", gender=" + gender + ", department=" + department + "]";
	}

	public Employee(Integer id, String lastName, Date birth, String email,
			Integer gender, Department department) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.birth = birth;
		this.email = email;
		this.gender = gender;
		this.department = department;
	}

	public Employee(Integer id, String lastName, String email,
			Integer gender, Department department) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.department = department;
	}

	
	
	
}
