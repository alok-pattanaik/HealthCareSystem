<%@page import="com.cg.hcs.service.DiagnosticCenterServiceImpl"%>
<%@page import="com.cg.hcs.service.IDiagnosticCenterService"%>
<%@page import="com.cg.hcs.entity.DiagnosticCenter"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View All Centers</title>
<link rel="stylesheet" type=" text/css" href="css/LoginRegister.css">
<link rel="stylesheet" type=" text/css" href="css/showCenters.css">
</head>
<body>
	<%
		if (session.getAttribute("loggedInStatus") == "admin") {
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
					<li><a href="adminHomePage.jsp">Home</a></li>
					<li><a href="LogoutServlet">Logout</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<div class="box">
		<%
			if (request.getAttribute("centerDeleted") != null) {
					out.println("<h1>" + request.getAttribute("centerDeleted") + "</h1>");
				}
				if (request.getAttribute("approveAppointment") != null) {
		%>
		<h1>
			<%
				out.println(request.getAttribute("approveAppointment"));
			%>
		</h1>
		<%
			}
		%>
		<div class="tableFixHead">
			<table border="4" ,class="table-style" cellpadding="15%">
				<tr class="table-heading">
					<th style="position: sticky; top: 0">Center Id
					<th style="position: sticky; top: 0">Center Name
					<th style="position: sticky; top: 0">Center Address
					<th style="position: sticky; top: 0">Center Contact No
					<th colspan="2" style="position: sticky; top: 0">Test
					<th style="position: sticky; top: 0">Approve Appointment
					<th style="position: sticky; top: 0">Center
				</tr>
				<%
					IDiagnosticCenterService diagnosticCenterService = new DiagnosticCenterServiceImpl();
						List<DiagnosticCenter> diagnosticCenters = diagnosticCenterService.viewAllCenters();

						for (DiagnosticCenter center : diagnosticCenters) {
							String centerId = center.getCenterId();
				%>
				<tr class="table-row">
					<td><%=center.getCenterId()%>
					<td><%=center.getCenterName()%>
					<td><%=center.getCenterAddress()%>
					<td><%=center.getContactNumber()%>
					<td><a href="addTest.jsp?centerId=<%=centerId%>"><button>Add
								Test</button></a>
					<td><a href="removeTest.jsp?centerId=<%=centerId%>"><button>Remove
								Test</button></a>
					<td><a
						href="approveRejectAppointment.jsp?centerId=<%=centerId%>"><button>Approve
								Appointment</button></a>
					<td><a href="DeleteCenterServlet?centerId=<%=centerId%>"><button>Delete
								Center</button></a>
				</tr>
				<%
					}
				%>
			</table>
		</div>
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