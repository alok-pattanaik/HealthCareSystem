<%@page import="com.cg.hcs.entity.Appointment"%>
<%@page import="java.util.List"%>
<%@page import="com.cg.hcs.service.AppointmentServiceImpl"%>
<%@page import="com.cg.hcs.service.IAppointmentService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	window.history.forward();
	function noBack()
	{
		window.history.forward();
	}
</script>
<meta charset="ISO-8859-1">
<title>Approve Reject Appointment</title>
</head>
<link rel="stylesheet" type " text/css" href="css/LoginRegister.css">
<link rel="stylesheet" type " text/css" href="css/showCenters.css">
<body>
	<header id="header">
		<div class="container">
			<div id="branding">
				<!--<h1><span class = "highlight">ONE</span> STOP DOCS</h1>-->
				<!-- <h1 class="logo">HCS</h1> -->
				<!--<img src="logo-1.png" class="img" alt="logo">-->
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
	<%
		if (request.getAttribute("approveAppointment") != null) {
	%>
	<center style="color: white">
		<h1>
			<%
				out.println(request.getAttribute("approveAppointment"));
					/* request.setAttribute("centerId", request.getAttribute("centerId")); */
			%>
		</h1>
	</center>
	<%
				}
			%>
	<%
				if (session.getAttribute("loggedInStatus") == "admin") {
			%>
	<%
				IAppointmentService appointmentService = new AppointmentServiceImpl();
					String centerId = request.getParameter("centerId");
			/* 		request.setAttribute("centerId", centerId); */
					List<Appointment> appointmentList = appointmentService.viewAllAppointmentsByCenter(centerId);
					if (appointmentList.isEmpty()) {
					%>
					<center style="color: white">
					<% 
						out.println("<h1>No Appointments Yet</h1>");
					%>
					</center>
					<% 
					} else {
			%>
		<div class="box" style="width:90%">
	<div class="tableFixHead">
		<table border="4" ,class="table-style" cellpadding="15%" style="width:100%">
			<tr class="table-heading">
				<th>Appointment Id
				<th>Test Name
				<th>Center Name
				<th>Appointment Date Time
				<th>User Id
				<th>Status
				<th colspan="2">Approve/Reject Appointment
			</tr>
			<%
						for (Appointment appointment : appointmentList) {
									int appId = appointment.getAppId();
									if (appointment.getAppStatus() == 'P') {
					%>
			<tr class="table-row">
				<td><%=appId%>
				<td><%=appointment.getTest().getTestName()%>
				<td><%=appointment.getCenter().getCenterName()%>
				<td><%=appointment.getAppDate()%>
				<td><%=appointment.getUser().getUserId()%>
				<td>Pending
				<td><a
					href="ChangeAppointmentStatusServlet?appId=<%=appId%>&appStatus=A&centerId=<%=centerId %>"><button>Approve</button></a>
				<td><a
					href="ChangeAppointmentStatusServlet?appId=<%=appId%>&appStatus=R&centerId=<%=centerId %>"><button>Reject</button></a>
			</tr>
			<%
						}
								}
								for (Appointment appointment : appointmentList) {
									int appId = appointment.getAppId();
									if (appointment.getAppStatus() != 'P') {
					%>
			<tr class="table-row">
				<td><%=appId%>
				<td><%=appointment.getTest().getTestName()%>
				<td><%=appointment.getCenter().getCenterName()%>
				<td><%=appointment.getAppDate()%>
				<td><%=appointment.getUser().getUserId()%>4 <%
							if (appointment.getAppStatus() == 'A') {
						%>
				<td>Approved <%
							} else {
						%>
				<td>Rejected <%
							}
						%>
				<td><button disabled="disabled">Approve</button> </a>
				<td><button disabled="disabled">Reject</button> </a>
			</tr>
			<%
						}
								}
					%>
		</table>
	</div>
	</div>

	<%
				}
			%>
	<%
				} else {
					request.getRequestDispatcher("errorPage.jsp").forward(request, response);
				}
			%>
	<footer>
		<p>© Health Care System, 2020, Developed by Group 4</p>
	</footer>
</body>
</html>