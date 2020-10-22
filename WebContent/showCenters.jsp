<%@page import="com.cg.hcs.entity.DiagnosticCenter"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Show Centers</title>
<link rel="stylesheet" type " text/css" href="css/LoginRegister.css">
<link rel="stylesheet" type " text/css" href="css/showCenters.css">
</head>
<body>
<%
		if (session.getAttribute("loggedInStatus") == "user") {
	%>
	<header id="header">
	<div class="container">
		<div id="branding">
				<!--<h1><span class = "highlight">ONE</span> STOP DOCS</h1>-->
				<!-- <h1 class="logo">HCS</h1> -->
				<!--<img src="logo-1.png" class="img" alt="logo">-->
				<div class="logo-image">
					<img src="images/logo.png" style="width:30%;height:90%;"></img>
					</div>
			</div>
		<nav>
		<ul>
			<li><a href="userHomePage.jsp">Home</a></li>
			<li><a href="LogoutServlet">Logout</a></li>
		</ul>
		</nav>
	</div>
	</header>

	<div class="centers">
		<div class="search-box">
			<form action="SearchByLocation" method="post">
				<table>
					<tr>
						<td><input type="text" placeholder="SearchByLocation"
							name="location"></td>
						<td><input type="submit" value="Search"></td>
				</table>
			</form>

		</div>
	</div>
	<div class="table-div">
		<center>
			<table border="4" ,class="table-style" cellpadding="15%">
				<tr class="table-heading">
					<th>CenterName
					<th>centerAddress
					<th>Contact Number
					<th>Link
				</tr>
				<%
					ArrayList<DiagnosticCenter> centersList = (ArrayList<DiagnosticCenter>) request.getAttribute("centersList");
					for (DiagnosticCenter center : centersList) {
				%>
				<tr class="table-row" >
					<td><%=center.getCenterName()%></td>
					<td><%=center.getCenterAddress()%></td>
					<td><%=center.getContactNumber()%></td>
					<td><a
						href="makeAppointment.jsp?centerId=<%=center.getCenterId()%>&centerName=<%=center.getCenterName()%>">BookAppointment</a></td>
				</tr>

				<%
					}
				%>
			</table>
		</center>

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