<%@page import="learn.basics.beans.Staff"%>
<%@page import="learn.basics.dao.StaffDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View</title>
</head>
<body>
	<%
	int staffId = Integer.parseInt(request.getParameter("id"));
	StaffDAO dao = new StaffDAO();
	Staff staff = dao.getStaffById(staffId);
	%>
	<h2>Staff Detail Information</h2>
	<h3>Name : <%out.print(staff.getName());%></h3>
	<h3>Language : <%out.print(staff.getLanguage());%></h3>
	<a href="<%=request.getContextPath()%>">Back</a>
</body>
</html>