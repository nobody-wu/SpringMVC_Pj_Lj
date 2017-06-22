package com.lj.springmvc.crud.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * �Զ���������ʵ�ֲ���
 * 1.����Զ�����������
 * 2.�������ļ���������ã���mvc:interceptors�ڵ������������bean
 * 
 * ���������ʱ��ִ��˳��
 * preHandle����˳��ִ��
 * postHandle���շ�˳��ִ��
 * afterCompletion���շ�˳��ִ��
 * 
 * 
 * 
 * ִ�ж��������
 * ���ڶ�����������pre����false֮��ִ��˳��Ϊ��һ����������pre���ڶ�����������pre��Ȼ���ǵ�һ����������after
 * 
 * 
 * @author lijian
 *
 */
public class FirstInterceptors implements HandlerInterceptor{

	/**
	 * ��Ⱦ��ͼ֮�󱻵���
	 * �ͷ���Դ
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("FirstInterceptors afterCompletion");
	}

	/**
	 * ����Ŀ�귽��֮�󣬵�����Ⱦ��ͼ֮ǰ�����õ�
	 * ���Զ��������е����Ի�����ͼ�����޸�
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("FirstInterceptors postHandle");
	}

	/**
	 * �÷�����Ŀ�귽��ִ��ǰ������
	 * ����ֵΪtrue��false�����𣬷���false�Ļ���ִ�е���������������Ͳ�����ִ����������������Ŀ�귽��������true�����ִ����������������Ŀ�귽��
	 * ���Կ���Ȩ�ޣ���־�������
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("FirstInterceptors preHandle");
		return true;
	}

}
