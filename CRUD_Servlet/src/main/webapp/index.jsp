<%@page import="learn.basics.beans.Staff"%>
<%@page import="java.util.List"%>
<%@page import="learn.basics.dao.StaffDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Staff Management</h2>
	<table border="1" style="font-size: 150%; font-family: inherit; font-style: normal; background-color: window; padding: 5">
		<thead>
			<tr>
				<td>ID</td>
				<td>Name</td>
				<td>Language</td>
				<td>Update</td>
				<td>Delete</td>
				<td>View</td>
			</tr>
		</thead>
		<tbody>
		<% 
		StaffDAO staffDAO = new StaffDAO();
		List<Staff> staffs = staffDAO.getAllStaff();
		int lastId = -1;
		for (Staff staff : staffs) {
			lastId = staff.getId();
		%>
		<tr>
			<td><%out.print(staff.getId());%></td>
			<td><%out.print(staff.getName());%></td>
			<td><%out.print(staff.getLanguage());%></td>
			<td><a href="?id=<%out.print(staff.getId());%>">Edit</a></td>
			<td><a href="?id=<%out.print(staff.getId());%>&btn=del">Delete</a></td>
			<td><a href="staff?id=<%out.print(staff.getId());%>">View</a></td>
		</tr>
		<%
		}
		%>
		</tbody>
	</table>
	<%
	if (request.getParameter("id") != null) {
	%>
	<a href="<%out.print(request.getContextPath());%>">Add new staff</a>
	<%
	}
	if (lastId > -1) {
		lastId++;
	}
	int staffId = 0;
	Staff staff = null;
	if (request.getParameter("id") != null) {
		staffId = Integer.parseInt(request.getParameter("id"));
		staff = staffDAO.getStaffById(staffId);
		lastId = staffId;
	}
	String title = (staffId > 0 && staff != null) ? "Update staff" : "Add new staff";
	String name = (staffId > 0 && staff != null) ? staff.getName() : "";
	String language = (staffId > 0 && staff != null) ? staff.getLanguage() : "";
	String action = (staffId > 0 && staff != null) ? "Save" : "Create";
	if (request.getParameter("btn") != null) {
		action = "Delete";
		title = "Delete staff";
	}
	%>
	<h3><%out.print(title);%></h3>
	<form action="staff" method="post">
		<table border="1" style="font-size: 150%;">
			<tr>
				<td>Name</td>
				<td>
					<input name="name" type="text" value="<%out.print(name);%>">
					
					<input name="id" type="hidden" value="<%out.print(lastId);%>">
				</td>
			</tr>
			<tr>
				<td>Language</td>
				<td> <input name="language" type="text" value="<%out.print(language);%>"></td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="submit" name="action" value=<%out.print(action);%> style="font-size: 150%"></td>
			</tr>
		</table>
	</form>
</body>
</html>