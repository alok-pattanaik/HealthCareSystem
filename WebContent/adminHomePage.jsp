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
<title>Admin Home Page</title>
<link rel="stylesheet" type="text/css" href="css/LoginRegister.css">

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
			<li><a href="createCenter.jsp">Create Center</a></li>
			<li><a href="viewAllCenters.jsp">View Centers</a></li>
			<li><a href="LogoutServlet">Logout</a></li>
		</ul>
		</nav>
	</div>
	</header>
	<%
		if (request.getAttribute("centerId") != null) {
	%>
	<center style="color: white">
		<h1>
			<%
				out.println(request.getAttribute("centerId"));
			%>
		</h1>
	</center>
	<%
		}
	%>
					<footer>
					<p>© Health Care System, 2020, Developed by Group 4</p>
					</footer>
					<%
						} else
							response.sendRedirect("errorPage.jsp");
					%>
					<!--<h1>Admin HomePage</h1>
	 <form action="CreateCenterServlet" method="post">
		<input type="text" name="centerName">
		<input type="submit" value="Create Center">
	</form> -->
</body>
</html>