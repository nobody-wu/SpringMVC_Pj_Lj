package com.lj.springmvc.crud.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.lj.springmvc.crud.entities.Department;
import com.lj.springmvc.crud.entities.Employee;
@Component
public class EmployeeConverter implements Converter<String,Employee>{

	@Override
	public Employee convert(String source) {
		// TODO Auto-generated method stub
		if(source!=null)
		{
			String[] splits=source.split("-");

			if(splits!=null&&splits.length==4)
			{

				String lastName=splits[0];
				String emailString=splits[1];
				Integer gender=Integer.parseInt(splits[2]) ;
				Integer departmentID=Integer.parseInt(splits[3]) ;
				Department department=new Department();
				department.setId(departmentID);
	
				Employee employee=new Employee(null,lastName,emailString,gender,department);

				System.out.println("Source:"+source+";Converter:"+employee);
				return employee;
			}
		}
		System.out.println("begin convert is null");
		return null;
	}

}
