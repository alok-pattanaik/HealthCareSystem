<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Test</title>
<link rel="stylesheet" type="text/css" href="css/LoginRegister.css">
</head>
<body>
	<%
		if (session.getAttribute("loggedInStatus") == "admin") {
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
					<li><a href="viewAllCenters.jsp">View Centers</a></li>
					<li class="current"><a href="adminHomePage.jsp">Home</a></li>

				</ul>
			</nav>
		</div>
	</header>
	<%
		if (request.getAttribute("testId") != null) {
	%>
	<center style="color: white">
		<h1>
			<%
				out.println(request.getAttribute("testId") + " added successfully");
			%>
		</h1>
		<center>
			<%
				}
			%>
			<div class="box">
				<form action="AddTestServlet" method="post">
					<table>
						<tr>
							<td>Center Id:
							<td><input type="text" name="centerId"
								value=<%=request.getParameter("centerId")%> readonly>
						<tr>
							<td>Test Name :
							<td><input type="text" name="testName"
								placeholder="Test Name" pattern="[A-za-z0-9 ]*" minlength="5"
								maxlength="20"
								title="Start with capital letter. Minimum length : 5, Maximum length : 14"
								required>
						<tr>
							<td>
							<td><input type="submit" value="Add Test">
					</table>
				</form>
			</div>


			<footer>
				<p>© Health Care System, 2020, Developed by Group 4</p>
			</footer>
</body>
<%
	} else {
		request.getRequestDispatcher("errorPage.jsp").forward(request, response);
	}
%>
</html>