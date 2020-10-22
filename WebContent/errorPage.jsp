
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HCS Home</title>
<link rel="stylesheet" type " text/css" href="css/Error.css">

<style>
.button3 {
	background-color: white;
	color: black;
	border: 2px solid #f44336;
	padding: 15px 32px;
	font-size: 16px;
}

.button3:hover {
	background-color: #f44336;
	color: white;
}

.center {
	padding: 300px 0;
	text-align: center;
}
</style>
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
					<li><a href="about.html">About</a></li>
					<li><a href="registration.jsp">Register</a></li>
					<li><a href="login.jsp">Login</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<div class="center" style="font-size: 30px">
		<strong>You are not authorised to access this! Please
			login/register to continue!<strong> <!-- <br /> <br />
				<button onclick="location.href='index.jsp'" type="button"
					class="button button3" href="index.jsp">Go to Homepage</button> -->
	</div>

	<footer>
		<p>© Health Care System, 2020, Developed by Group 4</p>
	</footer>

</body>
</html>
