<%@page import="com.cg.hcs.entity.Appointment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type " text/css" href="css/LoginRegister.css">
<link rel="stylesheet" type="text/css"
	href="css/checkAppointmentStatus.css">
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
	<!-- <div style="overflow: auto; height: 90%; max-height: 500px"> -->
		<div class="tableFixHead">
			<center>
				<table border="4" ,class="table-style" cellpadding="15%">
					<tr class="table-heading">
						<th style="position:sticky;top:0">CenterName
						<th style="position:sticky;top:0">TestName
						<th style="position:sticky;top:0">AppointmentDate & time
						<th style="position:sticky;top:0">Status
					</tr>
					<%
						List<Appointment> appointmentList = (List<Appointment>) request.getAttribute("appointmentList");
							for (Appointment appointment : appointmentList) {
					%>
					<tr class="table-row">
						<td><%=appointment.getCenter().getCenterName()%>
						<td><%=appointment.getTest().getTestName()%>
						<td><%=appointment.getAppDate()%> <%
 	String status = "";
 			if (appointment.getAppStatus() == 'P') {
 				status = "Pending";
 			} else if (appointment.getAppStatus() == 'A') {
 				status = "Approved";
 			} else {
 				status = "Rejected";
 			}
 %>
						<td><%=status%>
					</tr>
					<%
						}
					%>

				</table>
			</center>
		</div>
	<!-- </div> -->
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