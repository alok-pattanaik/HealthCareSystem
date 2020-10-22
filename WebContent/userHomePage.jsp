<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	window.history.forward();
	function noBack()
	{
		window.history.forward();
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User HomePage</title>

<link rel="stylesheet" type="text/css" href="css/userHome.css">
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
				<img src="images/logo.png" style="width: 30%; height: 90%;"></img>
			</div>
		</div>

		<nav>
		<ul>
			<li><a href="ShowDiagnosticCenterServlet">Make Appointment</a></li>
			<li><a href="CheckAppointmentStatusServlet">Check
					Appointment Status</a></li>
			<div class="dropdown">
				<button class="dropbtn">
					Welcome
					<%=session.getAttribute("name")%></button>
				<div class="dropdown-content">
					<a href="editProfile.jsp">Edit Profile</a> <a
						href="changePassword.jsp">Change Password</a> <a
						href="LogoutServlet">Logout</a>
				</div>
			</div>
		</ul>
		</nav>
	</div>
	</header>

	<%
		if (request.getAttribute("appointmentBooked") != null) {
	%>
	<center style="color: white">
		<h1>
			<%
				out.println(request.getAttribute("appointmentBooked"));
			%>
		</h1>
		</center>
			<%
				}
			%>
			<script>
				
			<%if (request.getAttribute("successMessage") != null) {%>
				setTimeout(
						function() {

							document.getElementById("successMessage").style.display = "none";
						}, 5000);
			<%request.setAttribute("successMessage", null);

				}%>
				
			</script>
		</center>
		<!-- <div class="center">
			<button class="button button2">Make Appointment</button>
			<button class="button button2">Check Appointment Status</button>

		</div> -->
		<footer>
		<p>© Health Care System, 2020, Developed by Group 4</p>
		</footer>
		<%
			} else
				response.sendRedirect("login.jsp");
		%>
	
</body>
</html>