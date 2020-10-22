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
					<li class="current"><a href="adminHomePage.jsp">Home</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<% 
		if(request.getAttribute("testId")!=null){
			%>
			<center style="color:white">
			<h1><%out.println(request.getAttribute("testId")+" added successfully");%></h1>
			<center>
			<%
		} 
		if(request.getAttribute("testNotAdded")!=null)
		{
	%>
	<center style="color:white">
			<h1><%out.println(request.getAttribute("testNotAdded"));%></h1>
			<center>
	<%} %>
	<div>
	
	</div>
	<%
	if(request.getParameter("addMultiple")!=null)
		{
			String centerId = request.getParameter("addMultiple");
			ITestService testService = new TestServiceImpl();
			List<Test> listOfTests = testService.viewAllTest(centerId);
			%>
		<div class="box">
		<form action="AddTestServlet" method="post">
			<table>
				<tr>
					<td>Center Id:
					<td><input type="text" name="centerId"
						value=<%=centerId%> readonly>
				<tr>
					<td>Select Test
					<td></td>
					<%for(Test test : listOfTests)
						{
							String testName = test.getTestName();
							int flag=0;
							for(int i=0; i<testName.length();i++){
								if(testName.charAt(i)==' '){
									flag++;
								}		
							}
							if(flag>1){
								continue;
							}
						%>
						<tr>
					<td><input type="checkbox" name="testNames" value="<%=test.getTestName() %>" >
					<td><label><%=test.getTestName() %></label>
					</tr>
					<%} %>
				<tr>
					<td>
					<td><input type="submit" value="Add Test">
			</table>
		</form>
	</div>
			<%
		} 
	else
	{
		%>
	<div class="box">
		<form action="addTest.jsp">
			<input type="text" value="<%=request.getParameter("centerId") %>" name="addMultiple" hidden>
			<input type="submit" value="Add Test Combination" style="width:90%">
		</form>
		<form action="AddTestServlet" method="post">
			<table>
				<tr>
					<td>Center Id:
					<td><input type="text" name="centerId"
						value=<%=request.getParameter("centerId")%> readonly>
				<tr>
					<td>Test Name :
					<td><input type="text" name="testName" placeholder="Test Name" pattern="[A-za-z0-9 ]*" minlength="5" maxlength="20" title="Start with capital letter. Minimum length : 5, Maximum length : 14" required>
				<tr>
					<td>
					<td><input type="submit" value="Add Test">
			</table>
		</form>
	</div>

<%} %>
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