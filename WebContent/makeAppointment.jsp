
<%@page import="com.cg.hcs.service.TestServiceImpl"%>
<%@page import="com.cg.hcs.service.ITestService"%>
<%@page import="com.cg.hcs.entity.Test"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Make Appointment</title>
<link rel="stylesheet" type="text/css" href="css/LoginRegister.css">
<link rel="stylesheet" type =" text/css" href="css/makeAppointment.css"><link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$(function() {
$("#sdatepicker").datepicker({
	minDate: 1,
    dateFormat: 'dd/mm/yy',
});
});
</script>

</head>

<body>
	<%
		if (session.getAttribute("loggedInStatus") == "user") {
	%>
	<header id="header">
	<div class="container">
		<div id="branding">
			<div class="logo-image">
				<img src="images/logo.png" style="width: 30%; height: 90%;"></img>
			</div>
		</div>
		<nav>
		<ul>
			<li class="current"><a href="userHomePage.jsp">Home</a></li>
			<li><a href="LogoutServlet">Logout</a></li>
		</ul>
		</nav>
	</div>
	</header>
	<%
		ITestService testService = new TestServiceImpl();
			String id = request.getParameter("centerId");
			List<Test> testList = testService.viewAllTest(id);
			System.out.println("In makeappointment.jsp\n" + id + " " + request.getParameter("centerName"));
	%>
	<div class="box">
		<form action="MakeAppointmentServlet" method="post">
			<%
				session.setAttribute("centerId", id);
			%>
			<table>
				<tr>
					<td>Selected Center</td>
					<td><input type="text"
						value=<%=request.getParameter("centerName")%> readonly></td>
				</tr>

				<tr>
					<td>Choose Test
					<td><select option="testList" id="testObj" name="testObj">
							<%
								for (Test test : testList) {
										System.out.println("Test id : " + test.getTestId());
							%>

							<option value=<%=test.getTestId()%>><%=test.getTestName()%></option>

							<%
								}
							%>
					</select></td>
				<tr>
					<td>Date</td>
					<td><input type="text" id="sdatepicker" name="date" required></td>
				</tr>
				<tr>
					<td>Time</td>
					<td><input type="time" name="time" id="time" /></td>
				</tr>

			</table>
			<input type="submit" name="Book Appointment">
		</form>
	</div>

	<footer>
	<p>© Health Care System, 2020, Developed by Group 4</p>
	</footer>
	<%
		} else {
			request.getRequestDispatcher("errorPage.jsp").forward(request, response);
		}
	%>
</body>
</html>