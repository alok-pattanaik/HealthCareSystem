<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

<link rel="stylesheet" type="text/css" href="css/LoginRegister.css">

</head>
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
					<li class="current"><a href="index.jsp">Home</a></li>
				</ul>
			</nav>
		</div>
	</header>
	
	<div class="image">
		<img src="images/login-registration.jpg"
			style="width: 100%; height: 82%;position:absolute"></img>

	</div>
	<div class="box">
		<h1>Login</h1>
		<%if(request.getAttribute("loginStatusFailed")!=null){%>
		<h1 style="color:red; "><%out.println(request.getAttribute("loginStatusFailed"));%></h1>
		<%}	%>
		<form action="LoginServlet" method="post">
			<br>
			<table>
				<tr>
					<td>UserId</td>
					<td>:</td>
					<td><input type="text" name="userid"></input></td>
				</tr>
				<tr>
					<td>Password</td>
					<td>:</td>
					<td><input type="password" name="password"></input></td>
				</tr>
			</table>
			<center>
				<input type="submit" name="s" value="submit">
			</center>
		</form>
	</div>

	<footer>
		<p>© Health Care System, 2020, Developed by Group 4</p>
	</footer>
</body>
</html>