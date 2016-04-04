<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Student Monitoring Application</title>
	<style>
			tr:first-child{
				font-weight: bold;
				background-color: #C6C9C4;
			}
	</style>
</head>
<body>
	<h2>List of Teachers</h2>	
	<table>
		<tr>
			<td>NAME</td><td>PHONE NUMBER</td><td>EMAIL</td><td>STATUS</td><td>TEACHER ID</td><td>PASSWORD</td><td></td>
		</tr>
		<c:forEach items="${teachers}" var="teacher">
			<tr>
			<td>${teacher.name}</td>
			<td>${teacher.phoneNumber}</td>
			<td>${teacher.email}</td>
			<td>${teacher.status}</td>
			<td>${teacher.password}</td>
			<td><a href="<c:url value='/edit-${teacher.teacherId}-teacher' />">${teacher.teacherId}</a></td>
			<td><a href="<c:url value='/delete-${teacher.teacherId}-teacher' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/newTeacher' />">Add New Teacher</a>
</body>
</html>