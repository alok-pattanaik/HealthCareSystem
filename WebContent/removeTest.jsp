<%@page import="com.cg.hcs.service.TestServiceImpl"%>
<%@page import="com.cg.hcs.service.ITestService"%>
<%@page import="com.cg.hcs.entity.Test"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" type= " text/css" href="css/LoginRegister.css">
<link rel="stylesheet" type= " text/css" href="css/showCenters.css">
</head>
<body>
<%
		if (session.getAttribute("loggedInStatus") == "admin") {
	%>
	<header id="header">
	<div class="container">
		<div id="branding">
				<div class="logo-image">
					<img src="images/logo.png" style="width:30%;height:90%;"></img>
					</div>
			</div>
		<nav>
		<ul>
		<li><a href="viewAllCenters.jsp">View Centers</a></li>
			<li><a href="adminHomePage.jsp">Home</a></li>
			<li><a href="LogoutServlet">Logout</a></li>
		</ul>
		</nav>
	</div>
	</header>
		<% 	if(request.getAttribute("testDeleted")!=null)
		{
			out.println("<h1 style='color:white'>"+request.getAttribute("testDeleted")+"</h1>");
		}%>
<div class="table-div">
	<%
		ITestService testService = new TestServiceImpl();
		String centerId = request.getParameter("centerId");
		List<Test> listOfTests = testService.viewAllTest(centerId);
		if(listOfTests!=null)
		{
			%>
			<div class="box">
			<h1>Center : <%=centerId %></h1>
			<table border="4" ,class="table-style" cellpadding="15%">
			<tr class="table-heading">
			<th>Test Id
			<th>Test Name
			<th>Remove
			</tr>
			<%for(Test test : listOfTests)
			{
				%>
				<tr class="table-row">
					<td><%=test.getTestId() %>
					<td><%=test.getTestName() %>
					<td><a href="RemoveTestServlet?testId=<%=test.getTestId()%>&centerId=<%=centerId %>"><button>Remove</button></a>
				</tr>
			<%}%>
			</table>
		<%}
	%>
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