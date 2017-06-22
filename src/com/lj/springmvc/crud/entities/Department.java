package com.lj.springmvc.crud.entities;

import java.io.Serializable;

public class Department implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7455922919411824261L;
	
	public Department()
	{}
	
	public Department(Integer id, String departmentName) {

		this.id = id;
		this.departmentName = departmentName;
	}
	
	
	
	private Integer id;
	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + departmentName
				+ "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	private String departmentName;
}
