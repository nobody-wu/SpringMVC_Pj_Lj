
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${contextPath }/SpringMVC_Pj_Lj/testConversionServices">
	employee:<input type="text" name="employee">
	<br>
	format:lastname-email-gender-departmen.id<br>
	fly:GG-lj@qq.com-0-105
	<br>
	<input type="submit" value="submit">
</form>
<br>
<br>
<!-- 
	1.为什么使用from标签
	可以快速开发出表单页面，而且可以更方便的进行表单值的回显
	2.注意：
	可以通过modelAttribute属性指定绑定的模型属性，
	若没有指定该属性，则默认从request域对象中都去command的表单bean
	如果该属性值也不存在，则会发生错误
 -->

<from:form action="${contextPath }/SpringMVC_Pj_Lj/emp" method="POST" modelAttribute="employee">
<!-- 设置显示所有的错误信息 -->
<from:errors path="*"></from:errors>
<br>
<c:if test="${employee.id==null }">
LastName : <from:input path="lastName"/>
<from:errors path="lastName"></from:errors>
</c:if>
<c:if test="${employee.id!=null }">
	<from:hidden path="id"></from:hidden>
	<input name="employee.id" type="text" value="${employee.id }">
	<input type="hidden" name="_method" value="put">
<!-- 	对于_method不能使用form:hidden标签 ，因为modelAttribute对应的bean中没有_method这个属性-->
</c:if>
<br>
Email    :<from:input path="email"/>
<from:errors path="email"></from:errors>
<br>
<%
	Map<String,String> genders=new HashMap();
	genders.put("0", "Male");
	genders.put("1", "Female");
	request.setAttribute("genders", genders);
%>
Gender    :<from:radiobuttons path="gender" items="${ genders}"/>
<br>
<!-- 
	1.数据类型转换 
	2.数据类型格式化 
 	3.数据校验 
 	  1)怎么校验？注解？
 	  ①使用JSR303验证标准
 	  ②加入hibernate validator验证框架
 	  ③在springmvc配置文件中添加mvc:annotation-driven
 	  ④需要在bean的属性上添加对应的注解
 	  ⑤在目标方法bean类型的前面添加@Valid注解
 	  2）验证出错怎么转向到哪一个页面
 	  注意：需验证的Bean对象和其他绑定结果对象或错误对象时成对出现的，它们之间不允许声明其他的入参
 	  3）错误消息？如何显示？如何把错误消息进行国际化
 -->
Birth:<from:input path="birth"/>fly:2012-12-12<br>
<from:errors path="birth"></from:errors>
<br>
Salary:<from:input path="salary"/> fly:1,111,111.1<br>
<from:errors path="salary"></from:errors>
<br>
Department:<from:select path="department.id" items="${departments }" itemLabel="departmentName" itemValue="id" delimiter="<br>">
</from:select>
<br>
<input type="submit" value="submit"/>
<br>

</from:form>


</body>
</html>