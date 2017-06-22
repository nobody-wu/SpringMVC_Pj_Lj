package com.lj.springmvc.crud.dao;
import com.lj.springmvc.crud.entities.*;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class EmployeeDao {
	private static Map<Integer,Employee> employees=null;
	private static Integer initId=1006;
	@Autowired
	private DepartmentDao departmentDao;
	
	static{
		employees=new HashMap<Integer, Employee>();
		
		employees.put(1001, new Employee(1001,"E-AA",null , "E-AA@qq.com", 1,new Department(101,"D-AA")));
		employees.put(1002, new Employee(1002,"E-BB", null, "E-BB@qq.com", 0,new Department(102,"D-BB")));
		employees.put(1003, new Employee(1003,"E-CC", null, "E-CC@qq.com", 0,new Department(103,"D-CC")));
		employees.put(1004, new Employee(1004,"E-DD", null, "E-DD@qq.com", 1,new Department(104,"D-DD")));
		employees.put(1005, new Employee(1005,"E-FF", null, "E-FF@qq.com", 1,new Department(105,"D-EE")));
		
	}
	
	public void save(Employee employee) {
		if(employee.getId()==null)
		{
			employee.setId(initId++);
		}

		employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
		employees.put(employee.getId(), employee);
	}
	
	public Collection<Employee> getEmployees()
	{
		return employees.values();
	}
	
	public Employee get(Integer id)
	{
		return employees.get(id);
	}
	
	public void delete(Integer id) {
		employees.remove(id);
	}
}
