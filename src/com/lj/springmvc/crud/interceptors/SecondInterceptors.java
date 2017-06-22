package com.lj.springmvc.crud.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器实现步骤
 * 1.添加自定义拦截器类
 * 2.在配置文件中添加配置，在mvc:interceptors节点添加拦截器的bean
 * 
 * @author lijian
 *
 */
public class SecondInterceptors implements HandlerInterceptor{

	/**
	 * 渲染视图之后被调用
	 * 释放资源
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("SecondInterceptors afterCompletion");
	}

	/**
	 * 调用目标方法之后，但是渲染视图之前被调用的
	 * 可以对请求域中的属性或者视图做出修改
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("SecondInterceptors postHandle");
	}

	/**
	 * 该方法在目标方法执行前被调用
	 * 返回值为true跟false的区别，返回false的话，执行到了这个拦截器，就不会再执行其他的拦截器和目标方法，返回true则继续执行其他的拦截器和目标方法
	 * 可以考虑权限，日志，事务等
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("SecondInterceptors preHandle");
		return true;
	}

}
