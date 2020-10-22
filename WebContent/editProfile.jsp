<%@page import="com.cg.hcs.entity.Users"%>
<%@page import="com.cg.hcs.dao.UserDAOImpl"%>
<%@page import="com.cg.hcs.dao.IUserDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<link rel="stylesheet" type="text/css" href="css/LoginRegister.css">
<script>
	function matchPassword() {

		var password = document.getElementById("changePassword").value;
		var confPassword = document.getElementById("confirmPassword").value;

		if (password == confPassword) {
			return true;
		}
		alert("Password and Confirm Password does not match");
		return false;
	}
</script>
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
			<li><a href="userHomePage.jsp">Home</a></li>
			<li><a href="LogoutServlet">Logout</a></li>
		</ul>
		</nav>
	</div>
	</header>


	<%
		String userId = (String) session.getAttribute("userId");
			IUserDAO userObj = new UserDAOImpl();
			Users user = userObj.getUser(userId);
	%>

	<div class="box">
		<form action="EditProfileServlet" method="post"
			onsubmit="return matchPassword()">
			<table>
				<tr>
					<td>User ID:</td>
					<td><input type="text" name="userId" id="userId"
						value="<%=userId%>" readonly></td>
				</tr>
				<tr>
					<td>Name:</td>
					<td><input type="text" name="username" id="username"
						value="<%=user.getUserName()%>"></td>
				</tr>
				<tr>
				<tr>
					<td>Phone Number:</td>
					<td><input type="tel" name="contactNo" id="contactNo"
						value="<%=user.getContactNo()%>"></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input type="email" name="email" id="email"
						value="<%=user.getEmail()%>"></td>
				</tr>
				<tr>
					<td><input type="submit" name="Edit" id="Edit" style="width:150px;"></td>
					<td><input type="reset" name="reset" id="reset" style="width:150px;"></td>
				</tr>
				<tr>
			</table>
		</form>
		</div>
	
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