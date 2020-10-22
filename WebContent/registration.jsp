<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%--  <%
if (session.getAttribute("userId") == null) {
	response.sendRedirect("index.jsp");
}

else {
	if (session.getAttribute("role").equals("usr")) {
		response.sendRedirect("UserHomePage.jsp");
	}
	if (session.getAttribute("role").equals("adm")) {
		response.sendRedirect("AdminHomePage.jsp");
	}
}
%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" type " text/css" href="css/LoginRegister.css">
</head>
<body>

	<header id="header">
	<div class="container">
		<div id="branding">
			<div class="logo-image">
				<img src="images/logo.png" style="width: 30%; height: 90%;"></img>
			</div>
		</div>
		<nav>
		<ul>
			<li class="current"><a href="index.jsp">Home</a></li>
			<li><a href="login.jsp">Login</a></li>
		</ul>
		</nav>
	</div>
	</header>
<% 
		if(request.getAttribute("userRegistered")!=null){
			%>
			<center style="color:white">
			<h1><%out.println(request.getAttribute("userRegistered"));%></h1>
			<center>
			<%
		} 
		
	%>
	<div class="box">
		<h1>User Registration</h1>
		<form action="RegisterServlet" method="post">
			<table>
				<!-- <tr>
				<td>UserId</td>
				<td><input type="text" id="userid" name="userid" value="" read only></td>
			</tr> -->
				<tr>
					<td>Username</td>
					<td>:</td>
					<td><input type="text" id="username" name="username" pattern="[A-Z][A-Za-z ]*" minlength="5" maxlength="20" title="Start with capital letter. Minimum length : 5, Maximum length : 14" required></td>

				</tr>
				<tr>
					<td>Password</td>
					<td>:</td>
					<td><input type="password" id="password" name="password"
						pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,15}$" title="Password should contain, A capital letter, A special character, A digit, Maximum length : 14" maxlength="14" required></td>
				</tr>
				<tr>
					<td>Contact no</td>
					<td>:</td>
					<td><input type="text" id="contactno" pattern="[6-9]\d{9}" name="contactno" title="Contact number should start with (6|7|8|9) and conatin 10 digits" required></td>
				</tr>
				<tr>
					<td>Email</td>
					<td>:</td>
					<td><input type="email" id="email" name="email" required></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td>
					</td>
				</tr>
			</table>
			<br>
			<center>
				<input type="submit" name="s" value="Register">
			</center>
		</form>
	</div>
	<footer>
	<p>© Health Care System, 2020, Developed by Group 4</p>
	</footer>
</body>
</html>





