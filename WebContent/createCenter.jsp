<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type " text/css" href="css/LoginRegister.css">
<link rel="stylesheet" type " text/css" href="css/showCenters.css">
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
			<li><a href="adminHomePage.jsp">Home</a></li>
			<li><a href="LogoutServlet">Logout</a></li>
		</ul>
		</nav>
	</div>
	</header>
	<div class="box">
	<h1>Create Center</h1>
		<form action="CreateCenterServlet" method="post">
			<table>
				<tr class="">
					<td>Center Name :
					<td><input type="text" name="centerName" pattern="[A-za-z]*" minlength="5" maxlength="20" title="Start with capital letter. Minimum length : 5, Maximum length : 14" required>
				<tr>
					<td>Center Address
					<td><textarea name="centerAddress"
							placeholder="Center Address" required></textarea>
				<tr>
					<td>Contact Number:
					<td><input type="text" name="contactNumber" pattern="[6|7|8|9]\d{9}" name="contactno" title="Contact number should start with (6|7|8|9) and conatin 10 digits" required>
				<tr>
					<td>
					<td><input type="submit" value="Create Center">
			</table>
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