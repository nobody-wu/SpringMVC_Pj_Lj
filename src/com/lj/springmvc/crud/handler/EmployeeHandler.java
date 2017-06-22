package com.lj.springmvc.crud.handler;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lj.springmvc.crud.dao.DepartmentDao;
import com.lj.springmvc.crud.dao.EmployeeDao;
import com.lj.springmvc.crud.entities.Department;
import com.lj.springmvc.crud.entities.Employee;


@Controller
public class EmployeeHandler {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	/**
	 * binder.setDisallowedFields("lastName");
	 * 设置了之后提交表单的时候不会对lastName赋值
	 * @param binder
	 */
//	@InitBinder
//	public void initBinder(WebDataBinder binder)
//	{
//		binder.setDisallowedFields("lastName");
//		
//	}
	
	
	@ModelAttribute
	public void getEmployee(@RequestParam(value="id",required=false) Integer id,Map<String,Object> map) {
		if(id!=null)
		{
			map.put("employee", employeeDao.get(id));
		}
	}
	
	
	@RequestMapping(value="/emp",method=RequestMethod.PUT)
	public String Update(Employee employee)
	{
		System.out.println(employee);
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	public String input(@PathVariable Integer id,Map<String,Object> map)
	{
		
		//employee 必须跟表单中的modelAttribute的属性值是一致的
		map.put("employee",employeeDao.get(id));
		map.put("departments", departmentDao.getDepartments());
		return "input";
	}
	
	@RequestMapping(value="emp/{id}",method=RequestMethod.DELETE)
	public String Delete(@PathVariable Integer id)
	{
		employeeDao.delete(id);
		return "redirect:/emps";
	}
	
	/**
	 * 
	 * @param employee
	 * @param bindingResult 获取错误信息,继承的是Errors
	 * @return
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public String Save(@Valid Employee employee,BindingResult bindingResult,Map<String,Object> map)
	{
		System.out.println(employee);
		if(bindingResult.getErrorCount()>0)
		{
			System.out.println("出错了");
			for (FieldError error : bindingResult.getFieldErrors()) {
				System.out.println(error.getField()+":"+error.getDefaultMessage());
			}
			//如果验证出错，则转向定制的页面
			map.put("departments", departmentDao.getDepartments());
			
			return "input";
		}
		
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	

	@RequestMapping(value="/emp",method=RequestMethod.GET)
	public String input(Map<String, Object> map)
	{
		map.put("departments", departmentDao.getDepartments());
		map.put("employee",new Employee() );
		return "input";
	}
	
	@RequestMapping("/emps")
	public String list(Map<String,Object> map) {
	
		map.put("employees", employeeDao.getEmployees());
		return "list";
	}
}
