package com.lj.springmvc.crud.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.lj.springmvc.crud.dao.EmployeeDao;
import com.lj.springmvc.crud.entities.Employee;

@Controller
public class SpringMVCTest {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private ResourceBundleMessageSource resourceMs;
	
	@RequestMapping(value="/testSimpleMappingExceptionResolver")
	public String testSimpleMappingExceptionResolver(@RequestParam("i") Integer i)
	{
		String[] varls=new String[10];
		System.out.println(varls[i]);
		System.out.println("testSimpleMappingExceptionResolver");
		return "success";
	}
	
	@RequestMapping(value="/testDefaulHandlerExceptionResolver",method=RequestMethod.POST)
	public String testDefaulHandlerExceptionResolver()
	{
		System.out.println("testDefaulHandlerExceptionResolver");
		return "success";
	}
	
	
//	@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="����")
	@RequestMapping("/testResponseStatusExceptionResolver")
	public String testResponseStatusExceptionResolver(@RequestParam("i") Integer i)
	{
		if(i==13)
		{
			throw new UserNameNotMatchPasswordException();
		}
		System.out.println("testResponseStatusExceptionResolver��������������������");
		return "success";
	}
	
	/**
	 * 1.��@ExceptionHandler��������п��Լ���Exception���͵Ĳ������ò�������Ӧ�������쳣����
	 * 2.@ExceptionHandler����������в��ܴ���Map����ϣ�����쳣��Ϣ����ҳ���ϣ���Ҫʹ��ModelAndView��Ϊ����ֵ
	 * 3.@ExceptionHandler��ǵķ��������ȼ�������
	 * 4.@ControllerAdvice�����Ҳ���@ExceptionHandler��������ǰ���쳣����ȥ@ControllerAdvice��ǵ�����ȥ�����쳣
	 * @param ex
	 * @return
	 */
//	@ExceptionHandler({ArithmeticException.class})
//	public ModelAndView handlerArithmeticException(Exception ex)
//	{
//
//		System.out.println("���쳣�ˣ�"+ex);
//		ModelAndView mv=new ModelAndView("error");
//		mv.addObject("exception",ex);
//		return mv;
//	}
//	
//	@ExceptionHandler({RuntimeException.class})
//	public ModelAndView handlerArithmeticException2(Exception ex)
//	{
//
//		System.out.println("[���쳣��]��"+ex);
//		ModelAndView mv=new ModelAndView("error");
//		mv.addObject("exception",ex);
//		return mv;
//	}
	
	@RequestMapping(value="/testExceptionHandlerExceptionResolver")
	public String testExceptionHandlerExceptionResolver(@RequestParam("i") Integer i)
	{
		System.out.println("result:"+10/i);
		return "success";
	}
	
	@RequestMapping(value="/testFileloadUpdate")
	public String testFileloadUpdate(@RequestParam(value="desc") String desc,@RequestParam(value="file") MultipartFile file) throws IOException
	{
		System.out.println("desc:"+desc);
		System.out.println("filename:"+file.getOriginalFilename());
		System.out.println("InputStream:"+file.getInputStream());
		return "success";
	}

	
	@RequestMapping("/i18n")
	public String testi18n(Locale locale)
	{
		String val=resourceMs.getMessage("i18n.user", null,locale);
		System.out.println(val);
		return "/i18n";
	}
	
	/**
	 * �ļ����ع���
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="testResposeEntity")
	public ResponseEntity<byte[]> testResposeEntity(HttpSession session) throws IOException
	{
		byte[] body=null;
		ServletContext servletContext=session.getServletContext();
		
		InputStream isInputStream=servletContext.getResourceAsStream("/files/download.txt");
		body=new byte[isInputStream.available()];
		isInputStream.read(body);
		
		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.add("Content-Disposition", "attachment:filename=download.txt");
		
		HttpStatus httpStatus= HttpStatus.OK;
		
		ResponseEntity<byte[]> responseEntity=new ResponseEntity<byte[]>(body,httpHeaders, httpStatus);
		return responseEntity;
	}
	
	@ResponseBody
	@RequestMapping(value="testHttpMessageConverter")
	public String testHttpMessageConverter(@RequestBody String body)
	{
		System.out.println("body:"+body);	
		return "hello! "+new Date();
	}
	
	@ResponseBody
	@RequestMapping(value="/testJson")
	public Collection<Employee> testJson()
	{
		return employeeDao.getEmployees();
	}
	
	@RequestMapping(value="/testConversionServices")
	public String testConverter(@RequestParam(value="employee") Employee employee) {
		if(employee!=null){
			System.out.println("save:" + employee);
		employeeDao.save(employee);
		}
		
		return "redirect:/emps";
	}
}
