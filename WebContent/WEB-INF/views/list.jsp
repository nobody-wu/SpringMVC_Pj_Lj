<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 
SpringMVC处理静态资源
1.为什么会有这样的问题？
优雅的REST风格的资源URL不希望.html或.do等后缀
若将DispatcherServlet请求映射配置为/，则SpringMVC将捕获WEB容器的所有请求，
包含静态资源的请求，SpringMVC会将他们当成一个普通的请求，因找不到对应处理器导致错误
2.解决：在SpringMVC的配置文件中配置<mvc:default-servlet-handler/>的方法解决静态资源的问题
 -->
<script type="text/javascript" src="scripts/jquery-1.3.2.min.js"></script>

<script type="text/javascript">
	$(function() {
		$(".delete").click(function() {
			var href = $(this).attr("href");
			$("form").attr("action", href).submit();
			return false;
		});

	})
</script>
</head>
<body>
	<form action="" method="POST">
		<input type="hidden" name="_method" value="DELETE" />
	</form>
	<c:if test="${empty requestScope.employees}">
		没有任何员工信息
	</c:if>


	<c:if test="${!empty requestScope.employees}">
		<table border="1" width="600">
			<tr>
				<th>ID</th>
				<th>LastName</th>
				<th>Email</th>
				<th>Gender</th>
				<th>Department</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>

			<c:forEach items="${requestScope.employees}" var="emp">
				<tr>
					<th>${emp.id }</th>
					<th>${emp.lastName }</th>
					<th>${emp.email }</th>
					<th>${emp.gender==0 ?'Female':'Male' }</th>
					<th>${emp.department.departmentName }</th>
					<th><a href="emp/${emp.id}">Edit</a></th>
					<th><a class="delete" href="emp/${emp.id }">delete</a></th>
				</tr>

			</c:forEach>
		</table>
	</c:if>

	<a href="emp">add new employees</a>
</body>
</html>